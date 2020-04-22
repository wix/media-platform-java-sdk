package com.wix.mediaplatform.v7.service.file;

import com.wix.mediaplatform.v7.BaseTest;
import com.wix.mediaplatform.v7.MediaPlatform;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.exception.ResourceNotFoundException;
import com.wix.mediaplatform.v7.metadata.FileMetadata;
import com.wix.mediaplatform.v7.service.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileServiceTest extends BaseTest {

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

    @Test
    public void getFileNotFound() {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(404)
                        .withBodyFile("get-file-not-found-response.json")));

        assertThrows(ResourceNotFoundException.class, () -> fileService.fileRequest()
                .setPath("/file.txt")
                .execute());
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
    public void uploadConfigurationRequest() throws Exception {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        UploadUrl response = fileService.uploadConfigurationRequest().execute();

        assertThat(response.getUploadToken(), is("some token"));
        assertThat(response.getUploadUrl(), is("http://localhost:8443/_api/upload/file"));
    }

    @Test
    public void uploadFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-v2-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileFromFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-v2-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getLocalFile())
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileWithLifecycle() throws Exception {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-response.json")));
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
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-response.json")));

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
                        .withBodyFile("file-upload-v2-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .execute();

        verify(exactly(2), postRequestedFor(urlEqualTo("/_api/upload/file")));
        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileError500MaxRetries() {
        try {
            stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("get-upload-configuration-response.json")));

            stubFor(post(urlEqualTo("/_api/upload/file"))
                    .willReturn(aResponse().withStatus(500)));

            assertThrows(MediaPlatformException.class, () -> fileService.uploadFileRequest()
                    .setPath("/a/new.txt")
                    .setMimeType("text/plain")
                    .setContent(getBytes())
                    .execute()
            );
        } finally {
            verify(exactly(MediaPlatform.MAX_RETRIES),
                    postRequestedFor(urlEqualTo("/_api/upload/file")));
        }
    }

    @Test
    public void uploadFileError401NoRetries() {
        try {
            stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("get-upload-configuration-response.json")));

            stubFor(post(urlEqualTo("/_api/upload/file"))
                    .willReturn(aResponse().withStatus(401)));

            assertThrows(MediaPlatformException.class, () -> fileService.uploadFileRequest()
                    .setPath("/a/new.txt")
                    .setMimeType("text/plain")
                    .setContent(getBytes())
                    .execute());
        } finally {
            verify(exactly(1), postRequestedFor(urlEqualTo("/_api/upload/file")));
        }
    }

    @Test
    public void uploadFileAlreadyExists() {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withStatus(409)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-already-exists-response.json")));

        assertThrows(MediaPlatformException.class, () -> fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .setLifecycle(new FileLifecycle()
                        .setAction(FileLifecycle.Action.DELETE)
                        .setAge(100))
                .execute()
        );
    }

    @Test
    public void uploadFileUnrecognizedError() {
        stubFor(post(urlEqualTo("/_api/v2/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-unrecognized-error-response.json")));

        assertThrows(MediaPlatformException.class, () -> fileService.uploadFileRequest()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .setLifecycle(new FileLifecycle()
                        .setAction(FileLifecycle.Action.DELETE)
                        .setAge(100))
                .execute()
        );
    }

    @Test
    public void uploadFileV3() throws Exception {
        stubFor(post(urlEqualTo("/_api/v3/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-v3-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-v2-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequestV3()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getBytes())
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileV3FromFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/v3/upload/configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-configuration-v3-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-v2-response.json")));

        FileDescriptor fileDescriptor = fileService.uploadFileRequestV3()
                .setPath("/a/new.txt")
                .setMimeType("text/plain")
                .setContent(getLocalFile())
                .execute();

        assertThat(fileDescriptor.getId(), is("c4516b12744b4ef08625f016a80aed3a"));
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
        assertThat(job.getStatus(), Matchers.is(Job.Status.pending));
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
        //noinspection ConstantConditions
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

    @Test
    public void getSignedUrl() throws Exception {
        String url = fileService.signedUrlRequest().setPath("/file.txt").execute();

        assertThat(url, startsWith("http://localhost:8443/file.txt?token="));
    }

    @Test
    public void getSignedUrlWithOptions() throws Exception {
        String url = fileService.signedUrlRequest()
                .setPath("/file.txt")
                .setOnExpireRedirectTo("url")
                .setAttachment(new Attachment()
                        .setFilename("fish"))
                .execute();

        assertThat(url, startsWith("http://localhost:8443/file.txt?token="));
        assertThat(url, containsString("&filename=fish"));
    }

    //    todo: test copy

    private byte[] getBytes() throws IOException {
        File file = getLocalFile();
        return Files.readAllBytes(file.toPath());
    }

    private File getLocalFile() {
        return new File(Objects.requireNonNull(this.getClass().getClassLoader()
                .getResource("source/image.jpg"))
                .getFile());
    }
}
