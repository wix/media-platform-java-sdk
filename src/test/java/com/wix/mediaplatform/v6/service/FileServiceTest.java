package com.wix.mediaplatform.v6.service;

import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.MediaPlatform;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.exception.ResourceNotFoundException;
import com.wix.mediaplatform.v6.metadata.FileMetadata;
import com.wix.mediaplatform.v6.service.file.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class FileServiceTest extends BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private FileService fileService = new FileService(configuration, authenticatedHttpClient, objectMapper,
            authenticator);

    @Test
    public void createFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/files"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-file-response.json")));

        FileDescriptor fileDescriptor = fileService.createFileRequest()
                .setPath("/newDirectory")
                .execute();

        assertThat(fileDescriptor.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
        assertThat(fileDescriptor.getType(), is(FileDescriptor.Type.DIRECTORY));
    }

    @Test
    public void copyFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/copy/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("copy-file-response.json")));

        FileDescriptor fileDescriptor = fileService.copyFileRequest()
                .setDestination(new Destination()
                        .setAcl(FileDescriptor.Acl.PUBLIC)
                        .setDirectory("/fish"))
                .setSource(new Source().setPath("/cat.jpg"))
                .execute();

        assertThat(fileDescriptor.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
        assertThat(fileDescriptor.getType(), is(FileDescriptor.Type.FILE));
    }

    @Test
    public void getFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-file-response.json")));

        FileDescriptor file = fileService.fileRequest()
                .setPath("/file.txt")
                .execute();

        assertThat(file.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getFileNotFound() throws Exception {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(404)
                        .withBodyFile("get-file-not-found-response.json")));

        fileService.fileRequest()
                .setPath("/file.txt")
                .execute();
    }

    @Test
    public void getFileMetadataByIdImage() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-image-response.json")));

        FileMetadata file = fileService.fileIdMetadataRequest("id").execute();

        assertThat(file.getFileDescriptor().getId(), is("2145ae56cd5c47c79c05d4cfef5f1078"));
    }

    @Test
    public void getFileMetadataByIdVideo() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-video-response.json")));

        FileMetadata file = fileService.fileIdMetadataRequest("id").execute();

        assertThat(file.getFileDescriptor().getId(), is("2de4305552004e0b9076183651030646"));
    }

    @Test
    public void getFileMetadataByPathImage() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/metadata?path=%2Fimages%2Fanimals%2Fcat.jpg"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-image-response.json")));

        FileMetadata file = fileService.fileMetadataRequest()
                .setPath("/images/animals/cat.jpg")
                .execute();

        assertThat(file.getFileDescriptor().getId(), is("2145ae56cd5c47c79c05d4cfef5f1078"));
    }

    @Test
    public void listFiles() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/ls_dir?path=%2F"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        FileList response = fileService.fileListRequest().setPath("/").execute();

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void listFilesWithAllParams() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/ls_dir?path=%2F&nextPageToken=fish&pageSize=200&orderBy=name&orderDirection=acs&r=yes&type=-"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        FileList response = fileService.fileListRequest()
                .setPath("/")
                .setNextPageToken("fish")
                .setRecursive(true)
                .setPageSize(200)
                .setType(FileDescriptor.Type.FILE)
                .setOrderBy(FileListRequest.OrderBy.name)
                .setOrderDirection(FileListRequest.OrderDirection.acs)
                .execute();

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void deleteFileById() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileService.deleteFileIdRequest("fileId").execute();
    }

    @Test
    public void deleteFileByPath() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileService.deleteFileRequest().setPath("/file.txt").execute();
    }

    @Test
    public void getUploadUrl() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        UploadUrl response = fileService.uploadUrlRequest().execute();

        assertThat(response.getUploadToken(), is("some token"));
        assertThat(response.getUploadUrl(), is("http://localhost:8443/_api/upload/file"));
    }

    @Test
    public void uploadFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileWithLifecycle() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-with-lifecycle-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .setLifecycle(new FileLifecycle()
                        .setAction(FileLifecycle.Action.DELETE)
                        .setAge(100))
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileError500OneRetry() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .inScenario("Error500OneRetry")
                .whenScenarioStateIs(STARTED)
                .willReturn(aResponse().withStatus(500))
                .willSetStateTo("afterOneError"));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .inScenario("Error500OneRetry")
                .whenScenarioStateIs("afterOneError")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .execute();

        verify(exactly(2), postRequestedFor(urlEqualTo("/_api/upload/file")));
        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileError500MaxRetries() throws Exception {
        try {
            stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("get-upload-url-response.json")));

            stubFor(post(urlEqualTo("/_api/upload/file"))
                    .willReturn(aResponse().withStatus(500)));

            thrown.expect(allOf(
                    instanceOf(MediaPlatformException.class),
                    hasProperty("code", equalTo(500)))
            );

            fileService.uploadFileRequest()
                    .setPath("/a/new.txt")
                    .setMimeType("text/plain")
                    .setContent(getBytes())
                    .execute();
        } finally {
            verify(exactly(MediaPlatform.MAX_RETRIES),
                    postRequestedFor(urlEqualTo("/_api/upload/file")));
        }
    }

    @Test
    public void uploadFileError401NoRetries() throws Exception {
        try {
            stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("get-upload-url-response.json")));

            stubFor(post(urlEqualTo("/_api/upload/file"))
                    .willReturn(aResponse().withStatus(401)));

            thrown.expect(allOf(
                    instanceOf(MediaPlatformException.class),
                    hasProperty("code", equalTo(401))));

            fileService.uploadFileRequest()
                    .setPath("/a/new.txt")
                    .setMimeType("text/plain")
                    .setContent(getBytes())
                    .execute();
        } finally {
            verify(exactly(1), postRequestedFor(urlEqualTo("/_api/upload/file")));
        }
    }

    @Test(expected = MediaPlatformException.class)
    public void uploadFileAlreadyExists() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withStatus(409)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-already-exists-response.json")));

        fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .setLifecycle(new FileLifecycle()
                        .setAction(FileLifecycle.Action.DELETE)
                        .setAge(100))
                .execute();
    }

    @Test(expected = MediaPlatformException.class)
    public void uploadFileUnrecognizedError() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-unrecognized-error-response.json")));

        fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .setLifecycle(new FileLifecycle()
                        .setAction(FileLifecycle.Action.DELETE)
                        .setAge(100))
                .execute();
    }

    @Test
    public void importFilePending() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-pending-response.json")));

        ImportFileJob job = fileService.importFileRequest()
                .setSourceUrl("http://source.url/filename.txt")
                .setDestination(new Destination()
                        .setAcl(FileDescriptor.Acl.PUBLIC)
                        .setDirectory("/fish"))
                .execute();
        ImportFileSpecification specification = job.getSpecification();

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
        assertThat(job.getStatus(), is(Job.Status.pending));
        assertThat(job.getType(), is("urn:job:import.file"));
        assertThat(specification.getSourceUrl(), is("http://source.url/filename.txt"));
        assertThat(specification.getDestination().getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(specification.getDestination().getDirectory(), is("/fish"));
    }

    @Test
    public void importFileSuccess() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-success-response.json")));

        ImportFileJob job = fileService.importFileRequest()
                .setSourceUrl("http://source.url/filename.ext")
                .setDestination(new Destination()
                        .setAcl(FileDescriptor.Acl.PUBLIC)
                        .setDirectory("/fish"))
                .execute();
        ImportFileSpecification specification = job.getSpecification();
        RestResponse<FileDescriptor> result = job.getResult();

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
        assertThat(job.getStatus(), is(Job.Status.success));
        assertThat(job.getType(), is("urn:job:import.file"));

        assertThat(specification.getSourceUrl(), is("http://source.url/filename.txt"));
        assertThat(specification.getDestination().getAcl(), is(FileDescriptor.Acl.PUBLIC));
        assertThat(specification.getDestination().getDirectory(), is("/fish"));

        assertThat(result.getCode(), is(0));
        assertThat(result.getPayload().getId(), is("123"));
        assertThat(result.getPayload().getHash(), is("456"));
        assertThat(result.getPayload().getPath(), is("/fish/filename.txt"));
        assertThat(result.getPayload().getMimeType(), is("text/plain"));
        assertThat(result.getPayload().getType(), is(FileDescriptor.Type.FILE));
        assertThat(result.getPayload().getSize(), is(100L));
        assertThat(result.getPayload().getAcl(), is(FileDescriptor.Acl.PUBLIC));
    }

    @Test
    public void getDownloadUrlDefault() throws Exception {
        String url = fileService.downloadUrlRequest().setPath("/file.txt").execute();

        assertThat(url, startsWith("http://localhost:8443/_api/download/file?downloadToken="));
    }

    @Test
    public void getDownloadUrlWithOptions() throws Exception {
        String url = fileService.downloadUrlRequest()
                .setPath("/file.txt")
                .setOnExpireRedirectTo("url")
                .setAttachment(new Attachment()
                        .setFilename("fish"))
                .execute();

        assertThat(url, startsWith("http://localhost:8443/_api/download/file?downloadToken="));
    }

    //    todo: test copy

    private byte[] getBytes() throws IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader()
                .getResource("source/image.jpg"))
                .getFile());
        return Files.readAllBytes(file.toPath());
    }
}