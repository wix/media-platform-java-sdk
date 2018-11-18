package com.wix.mediaplatform.v6.management;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.exception.FileNotFoundException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.metadata.FileMetadata;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.file.CreateFileRequest;
import com.wix.mediaplatform.v6.service.file.FileList;
import com.wix.mediaplatform.v6.service.file.FileListRequest;
import com.wix.mediaplatform.v6.service.file.FileService;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileServiceTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(configuration, authenticatedHttpClient, gson);

    private FileService fileService = new FileService(configuration, authenticatedHttpClient, fileUploader);

    @Test
    public void createFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/files"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-file-response.json")));

        CreateFileRequest createFileRequest = new CreateFileRequest().setPath("/newDirectory");

        FileDescriptor fileDescriptor = fileService.createFile(createFileRequest);

        assertThat(fileDescriptor.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
        assertThat(fileDescriptor.getType(), is(FileDescriptor.Type.DIRECTORY.getValue()));
    }

    @Test
    public void getFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-file-response.json")));

        FileDescriptor file = fileService.getFile("/file.txt");

        assertThat(file.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
    }

    @Test(expected = FileNotFoundException.class)
    public void getFileNotFound() throws Exception {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(404)
                        .withBodyFile("get-file-not-found-response.json")));

        fileService.getFile("/file.txt");
    }

    @Test
    public void getFileMetadataByIdImage() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-image-response.json")));

        FileMetadata file = fileService.getFileMetadataById("id");

        assertThat(file.getFileDescriptor().getId(), is("2145ae56cd5c47c79c05d4cfef5f1078"));
    }

    @Test
    public void getFileMetadataByPathImage() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/metadata?path=%2Fimages%2Fanimals%2Fcat.jpg"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-image-response.json")));

        FileMetadata file = fileService.getFileMetadataByPath("/images/animals/cat.jpg");

        assertThat(file.getFileDescriptor().getId(), is("2145ae56cd5c47c79c05d4cfef5f1078"));
    }

    @Test
    public void getFileMetadataByIdVideo() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-video-response.json")));

        FileMetadata file = fileService.getFileMetadataById("id");

        assertThat(file.getFileDescriptor().getId(), is("2de4305552004e0b9076183651030646"));
    }

    @Test
    public void listFiles() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/ls_dir?path=%2F"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        FileList response = fileService.listFiles("/", null);

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void listFilesWithAllParams() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/ls_dir?nextPageToken=fish&orderBy=name&orderDirection=acs&pageSize=200&path=%2F&r=yes&type=-"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        FileList response = fileService.listFiles("/", new FileListRequest().ascending()
                .setNextPageToken("fish")
                .setRecursive(true)
                .setPageSize(200)
                .setType(FileDescriptor.Type.FILE)
                .setOrderBy(FileListRequest.OrderBy.name)
        );

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void deleteFileById() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileService.deleteFileById("fileId");
    }

    @Test
    public void deleteFileByPath() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileService.deleteFileByPath("/file.txt");
    }
}