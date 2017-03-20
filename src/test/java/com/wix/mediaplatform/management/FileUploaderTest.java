package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
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
        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        GetUploadUrlResponse response = fileUploader.getUploadUrl(null);

        assertThat(response.getUploadToken(), is("token"));
        assertThat(response.getUploadUrl(), is("url"));
    }

    @Test
    public void uploadFile() throws Exception {
        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-upload-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        FileDescriptor[] files = fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, null);

        assertThat(files[0].getId(), is("id"));
    }
}
