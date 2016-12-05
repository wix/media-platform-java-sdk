package com.wix.mediaplatform.filedownloader;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.download.GetSecureUrlRequest;
import com.wix.mediaplatform.dto.download.GetSecureUrlResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileDownloaderTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);
    private FileDownloader fileDownloader = new FileDownloader(authenticatedHTTPClient, configuration);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getSecureUrls() throws Exception {
        Map<String,Object> additionalClaims = newHashMap();
        additionalClaims.put("encoding", "src");
        additionalClaims.put("save_as", "fish.jpg");
        when(authenticationFacade.getSelfSignedHeader("userId", additionalClaims)).thenReturn("header");

        stubFor(post(urlEqualTo("/secure-files/fileId/tickets/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-secure-download-url-response.json")));

        GetSecureUrlResponse[] response = fileDownloader.getSecureUrls("userId", new GetSecureUrlRequest()
                .setFileId("fileId")
                .addEncoding("src")
                .setSaveAs("fish.jpg")
        );

        assertThat(response[0].getPath(), is("/path_1"));
    }

    @Test
    public void getSecureUrlsBatch() throws Exception {
        Map<String,Object> additionalClaims = newHashMap();
        additionalClaims.put("encoding", "src");
        additionalClaims.put("save_as", "fish.jpg");
        when(authenticationFacade.getSelfSignedHeader("userId", additionalClaims)).thenReturn("header");

        stubFor(post(urlEqualTo("/secure-files/fileId/tickets/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-secure-download-url-response.json")));

        GetSecureUrlRequest[] requests = new GetSecureUrlRequest[10];
        for (int i = 0; i < 10; i++) {
            requests[i] =  new GetSecureUrlRequest()
                    .setFileId("fileId")
                    .addEncoding("src")
                    .setSaveAs("fish.jpg");
        }

        GetSecureUrlResponse[] response = fileDownloader.getSecureUrls("userId", requests);

        assertThat(response[0].getPath(), is("/path_1"));
        assertThat(response[29].getPath(), is("/path_3"));
    }
}