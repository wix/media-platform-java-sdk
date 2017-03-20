package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ListFilesRequest;
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
    public void listFiles() throws Exception {
        stubFor(get(urlEqualTo("/files/ls_dir"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        ListFilesResponse response = fileManager.listFiles("/", null);

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void listFilesWithOptions() throws Exception {
        stubFor(get(urlEqualTo("/files/getpage?cursor=cursor&page_size=10&order=-date&parent_folder_id=parentFolderId&media_type=video&tag=tag"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        ListFilesResponse response = fileManager.listFiles("userId", new ListFilesRequest()
                .setOrderBy(ListFilesRequest.OrderBy.date)
                .descending()
                .setNextPageToken("cursor")
                .setPageSize(10)
        );

        assertThat(response.getFiles().length, is(2));
    }

    @Test
    public void getFile() throws Exception {
        stubFor(get(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-file-image-response.json")));

        FileDescriptor file = fileManager.getFile("/file.txt");

        assertThat(file.getId(), is("id"));
    }

    @Test
    public void deleteFileById() throws Exception {
        stubFor(delete(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileManager.deleteFileById("fileId");
    }

    @Test
    public void deleteFileByPath() throws Exception {
        stubFor(delete(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("null-payload-response.json")));

        fileManager.deleteFileByPath("/file.txt");
    }
}