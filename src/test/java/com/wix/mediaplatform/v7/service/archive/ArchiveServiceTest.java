package com.wix.mediaplatform.v7.service.archive;

import com.wix.mediaplatform.v7.BaseTest;
import com.wix.mediaplatform.v7.service.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ArchiveServiceTest extends BaseTest {

    private ArchiveService archiveService = new ArchiveService(configuration, authenticatedHttpClient);

    @Test
    public void createArchive() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-archive-pending-response.json")));

        CreateArchiveJob job = archiveService.createArchiveRequest()
                .addSource(new ArchiveSource().setFileId("file id"))
                .setDestination(new Destination().setPath("/fish/file.zip").setAcl(FileDescriptor.Acl.PRIVATE))
                .setArchiveType("zip")
                .execute();

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void createArchiveWithLifecycle() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-archive-pending-response.json")));

        CreateArchiveJob job = archiveService.createArchiveRequest()
                .addSource(new ArchiveSource().setFileId("file id"))
                .setDestination(new Destination()
                        .setPath("/fish/file.zip")
                        .setAcl(FileDescriptor.Acl.PRIVATE)
                        .setFileLifecycle(new FileLifecycle()
                                .setAction(FileLifecycle.Action.DELETE)
                                .setAge(100)))
                .setArchiveType("zip")
                .execute();

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void createArchiveFromArchiveSource() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-archive-pending-response.json")));

        CreateArchiveJob job = archiveService.createArchiveRequest()
                .addSource(new ArchiveSource().setPathInArchive("/foo"))
                .setDestination(new Destination().setPath("/fish/file.zip").setAcl(FileDescriptor.Acl.PRIVATE))
                .setArchiveType("zip")
                .execute();

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void extractArchive() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-pending-response.json")));

        ExtractArchiveJob job = archiveService.extractArchiveRequest()
                .setSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setDirectory("/fish"))
                .execute();

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
    }

    @Test
    public void extractArchiveWithCallbackAndReportPending() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-with-report-pending-response.json")));

        ExtractArchiveJob job = archiveService.extractArchiveRequest()
                .setSource(new Source().setFileId("file id"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport(new ExtractedFilesReport()
                        .setDestination(new Destination()
                                .setPath("/report_dir/report.txt")
                                .setAcl(FileDescriptor.Acl.PUBLIC))
                        .setFormat(ExtractedFilesReport.Format.json))
                .setJobCallback(new Callback()
                        .setUrl("http://callback.url")
                        .setAttachment(new HashMap<String, Object>() {
                            {
                                put("attachmentKey", "attachmentValue");
                            }
                        })
                        .setHeaders(new HashMap<String, String>() {
                            {
                                put("headerKey", "headerValue");
                            }
                        }))
                .execute();

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is("urn:job:archive.extract"));
        assertThat(job.getStatus(), Matchers.is(Job.Status.pending));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        Callback callback = job.getCallback();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
        assertThat(callback.getUrl(), is("http://callback.url"));
        assertThat(callback.getAttachment(), is(new HashMap<String, Object>() {
            {
                put("attachmentKey", "attachmentValue");
            }
        }));
        assertThat(callback.getHeaders(), is(new HashMap<String, String>() {
            {
                put("headerKey", "headerValue");
            }
        }));
    }

    @Test
    public void extractArchiveWithReportSuccess() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-with-report-success-response.json")));

        ExtractArchiveJob job = archiveService.extractArchiveRequest()
                .setSource(new Source().setFileId("file id").setPath("/zips/zip1.zip"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport( new ExtractedFilesReport()
                        .setDestination(new Destination()
                                .setPath("/report_dir/report.json")
                                .setAcl(FileDescriptor.Acl.PUBLIC))
                        .setFormat(ExtractedFilesReport.Format.json))
                .execute();

        RestResponse<ExtractArchiveJobResult> jobResult = job.getResult();
        FileDescriptor reportFileDescriptor = jobResult.getPayload().getReportFileDescriptor();
        assertThat(jobResult.getCode(), is(0));
        assertThat(jobResult.getMessage(), is("OK"));
        assertThat(reportFileDescriptor.getPath(), is("/report_dir/report.json"));

        assertThat(reportFileDescriptor.getType(), is(FileDescriptor.Type.FILE));
        assertThat(reportFileDescriptor.getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(reportFileDescriptor.getId(), is("report file id"));
        assertThat(reportFileDescriptor.getSize(), is(1718L));
        assertThat(reportFileDescriptor.getMimeType(), is("application/json; charset=utf-8"));

        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is("urn:job:archive.extract"));
        assertThat(job.getStatus(), is(Job.Status.success));

        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();
        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }

    @Test
    public void extractArchiveWithoutReportSuccess() throws Exception {
        stubFor(post(urlEqualTo("/_api/archive/extract"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("extract-archive-without-report-success-response.json")));

        ExtractArchiveJob job =  archiveService.extractArchiveRequest()
                .setSource(new Source().setFileId("file id").setPath("/zips/zip1.zip"))
                .setDestination(new Destination().setDirectory("/fish"))
                .setExtractedFilesReport( new ExtractedFilesReport()
                        .setDestination(new Destination().setPath("/report_dir/report.json").setAcl(FileDescriptor.Acl.PUBLIC))
                        .setFormat(ExtractedFilesReport.Format.json))
                .execute();

        RestResponse<ExtractArchiveJobResult> jobResult = job.getResult();
        assertThat(jobResult.getCode(), is(0));
        assertThat(jobResult.getMessage(), is("OK"));
        assertThat(jobResult.getPayload().getReportFileDescriptor(), is(nullValue()));
        assertThat(job.getId(), is("6b4da966844d4ae09417300f3811849b_dd0ecc5cbaba4f1b9aba08cc6fa7348b"));
        assertThat(job.getType(), is("urn:job:archive.extract"));
        assertThat(job.getStatus(), is(Job.Status.success));
        ExtractArchiveSpecification extractArchiveSpecification = job.getSpecification();
        ExtractedFilesReport responseExtractedFilesReport = extractArchiveSpecification.getExtractedFilesReport();

        assertThat(responseExtractedFilesReport.getDestination().getDirectory(), is("/report_dir"));
        assertThat(responseExtractedFilesReport.getDestination().getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(responseExtractedFilesReport.getFormat(), is(ExtractedFilesReport.Format.json));
    }

    //    todo: test manifest creation
}
