package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ImportFileRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileUploaderTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(configuration, AuthenticatedHTTPClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getUploadUrl() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        GetUploadUrlResponse response = fileUploader.getUploadUrl(null);

        assertThat(response.getUploadToken(), is("some token"));
        assertThat(response.getUploadUrl(), is("https://localhost:8443/_api/upload/file"));
    }

    @Test
    public void uploadFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?path=%2Fa%2Fnew.txt&mimeType=text%2Fplain"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        FileDescriptor[] files = fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, null);

        assertThat(files[0].getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void importFilePending() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-pending-response.json")));

        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("http://source.url")
                .setDestination(new Destination()
                        .setAcl("public")
                        .setDirectory("/fish"));
        Job job = fileUploader.importFile(importFileRequest);

        System.out.println(job.toString());
    }

    @Test
    public void importFileSuccess() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-success-response.json")));

        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("http://source.url")
                .setDestination(new Destination()
                        .setAcl("public")
                        .setDirectory("/fish"));
        Job job = fileUploader.importFile(importFileRequest);

        System.out.println(job.toString());
    }
}
