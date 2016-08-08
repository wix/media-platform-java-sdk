package com.wix.mediaplatform.fileuploader;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileUploaderTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getUploadUrl() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        GetUploadUrlResponse response = fileUploader.getUploadUrl("userId");

        assertThat(response.getUploadToken(), is("token"));
        assertThat(response.getUploadUrl(), is("url"));
    }

    @Test
    public void uploadImageFromFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        ImageDTO imageDTO = fileUploader.uploadImage("userId", file, null);

        assertThat(imageDTO.getHeight(), is(17));
    }
}
