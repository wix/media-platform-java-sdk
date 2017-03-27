package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.request.CreateFileRequest;
import com.wix.mediaplatform.dto.response.ListFilesResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(configuration, authenticatedHttpClient);

    private FileManager fileManager = new FileManager(configuration, authenticatedHttpClient, fileUploader);

    @Test
    public void createFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/files"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-descriptor-response.json")));

        FileDescriptor fileDescriptor = fileManager.createFile(new CreateFileRequest()
                .setPath("/")
        );

        assertThat(fileDescriptor.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
    }

    @Test
    public void getFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-descriptor-response.json")));

        FileDescriptor file = fileManager.getFile("/file.txt");

        assertThat(file.getId(), is("d0e18fd468cd4e53bc2bbec3ca4a8676"));
    }

    @Test
    public void getFileMetadataByIdImage() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-image-response.json")));

        FileMetadata file = fileManager.getFileMetadataById("id");

        assertThat(file.getFileDescriptor().getId(), is("2145ae56cd5c47c79c05d4cfef5f1078"));
    }

    @Test
    public void getFileMetadataByIdVideo() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/id/metadata"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-metadata-video-response.json")));

        FileMetadata file = fileManager.getFileMetadataById("id");

        assertThat(file.getFileDescriptor().getId(), is("2de4305552004e0b9076183651030646"));
    }

    @Test
    public void listFiles() throws Exception {
        stubFor(get(urlEqualTo("/_api/files/ls_dir?path=%2F"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        ListFilesResponse response = fileManager.listFiles("/", null);

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void deleteFileById() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileManager.deleteFileById("fileId");
    }

    @Test
    public void deleteFileByPath() throws Exception {
        stubFor(delete(urlEqualTo("/_api/files?path=%2Ffile.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileManager.deleteFileByPath("/file.txt");
    }
}