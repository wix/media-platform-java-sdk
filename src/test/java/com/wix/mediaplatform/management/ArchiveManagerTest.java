package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.job.Source;
import com.wix.mediaplatform.dto.request.CreateArchiveRequest;
import com.wix.mediaplatform.dto.job.*;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ExtractArchiveRequest;
import com.wix.mediaplatform.dto.request.ExtractedFilesReport;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ArchiveManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private ArchiveManager archiveManager = new ArchiveManager(configuration, AuthenticatedHTTPClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void createArchive() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-archive-pending-response.json")));

        CreateArchiveRequest createArchiveRequest = new CreateArchiveRequest()
                .addSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setPath("/fish/file.zip").setAcl("private"))
                .setArchiveType("zip");
        Job job = archiveManager.createArchive(createArchiveRequest);

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void extractArchive() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-pending-response.json")));

        ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
                .setSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setDirectory("/fish"));
        ExtractArchiveJob job = archiveManager.extractArchive(extractArchiveRequest);

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void extractArchiveWithReportPending() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-with-report-pending-response.json")));

        ExtractedFilesReport requestExtractedFilesReport = new ExtractedFilesReport()
                .setDestination(new Destination().setPath("/report_dir/report.txt").setAcl("public"))
                .setFormat(ExtractedFilesReport.Format.json);

        ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
                .setSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport(requestExtractedFilesReport);

        ExtractArchiveJob job = archiveManager.extractArchive(extractArchiveRequest);

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is(Job.Type.ARCHIVE_EXTRACT.getValue()));
        assertThat(job.getStatus(), is(Job.Status.pending.name()));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is("public"));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }

    @Test
    public void extractArchiveWithReportSucess() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-with-report-success-response.json")));

        ExtractedFilesReport requestExtractedFilesReport = new ExtractedFilesReport()
                .setDestination(new Destination().setPath("/report_dir/report.json").setAcl("public"))
                .setFormat(ExtractedFilesReport.Format.json);

        ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
                .setSource(new Source().setFileId("file id").setPath("/zips/zip1.zip"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport(requestExtractedFilesReport);

        ExtractArchiveJob job = archiveManager.extractArchive(extractArchiveRequest);
        RestResponse<ExtractArchiveJobResult> jobResult = job.getResult();
        FileDescriptor reportFileDescriptor = jobResult.getPayload().getReportFileDescriptor();
        assertThat(jobResult.getCode(), is(0));
        assertThat(jobResult.getMessage(), is("OK"));
        assertThat(reportFileDescriptor.getPath(), is("/report_dir/report.json"));
        // TODO: Uncomment when we fix the hash issue
        // assertThat(reportFileDescriptor.getHash(), is("XXXX"));
        assertThat(reportFileDescriptor.getType(), is(FileDescriptor.Type.FILE.getValue()));
        assertThat(reportFileDescriptor.getAcl(), is(FileDescriptor.Acl.PUBLIC.getValue()));
        assertThat(reportFileDescriptor.getId(), is("report file id"));
        assertThat(reportFileDescriptor.getSize(), is(1718L));
        assertThat(reportFileDescriptor.getMimeType(), is("application/json; charset=utf-8"));

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is(Job.Type.ARCHIVE_EXTRACT.getValue()));
        assertThat(job.getStatus(), is(Job.Status.success.name()));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is("public"));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }

    @Test
    public void extractArchiveWithoutReportSucess() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-without-report-success-response.json")));

        ExtractedFilesReport requestExtractedFilesReport = new ExtractedFilesReport()
                .setDestination(new Destination().setPath("/report_dir/report.json").setAcl("public"))
                .setFormat(ExtractedFilesReport.Format.json);

        ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
                .setSource(new Source().setFileId("file id").setPath("/zips/zip1.zip"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport(requestExtractedFilesReport);

        ExtractArchiveJob job = archiveManager.extractArchive(extractArchiveRequest);
        RestResponse<ExtractArchiveJobResult> jobResult = job.getResult();
        assertThat(jobResult.getCode(), is(0));
        assertThat(jobResult.getMessage(), is("OK"));
        assertThat(jobResult.getPayload().getReportFileDescriptor(), is(nullValue()));
        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is(Job.Type.ARCHIVE_EXTRACT.getValue()));
        assertThat(job.getStatus(), is(Job.Status.success.name()));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is("public"));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }
}
