package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.*;
import com.wix.mediaplatform.dto.request.ExtractArchiveRequest;
import com.wix.mediaplatform.dto.request.ExtractedFilesReport;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    public void extractArchive() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-pending-response.json")));

        ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
                .setSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setDirectory("/fish"));
        ExtractArchiveJob job = (ExtractArchiveJob) archiveManager.extractArchive(extractArchiveRequest);

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void extractArchiveWithReport() throws Exception {
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

        ExtractArchiveJob job = (ExtractArchiveJob) archiveManager.extractArchive(extractArchiveRequest);

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is(Job.Type.ARCHIVE_EXTRACT.getValue()));
        assertThat(job.getStatus(), is(Job.Status.pending.name()));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is("public"));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }
}
