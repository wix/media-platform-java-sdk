package com.wix.mediaplatform.v7;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.wix.mediaplatform.v7.auth.Authenticator;
import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public abstract class BaseTest {

    private static int PORT = 8443;

    private OkHttpClient httpClient = MediaPlatform.getHttpClient();

    protected ObjectMapper objectMapper = MediaPlatform.getMapper();

    protected Configuration configuration = new Configuration("http", "localhost:" + PORT,
            "appId",
            "95eee2c63ac2d15270628664c84f6ddd");

    protected Authenticator authenticator = new Authenticator(configuration);

    protected AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient,
            objectMapper);

    //No-args constructor will start on port 8080, no HTTPS
    protected static WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(PORT));

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();

        WireMock.configureFor("localhost", PORT);
    }

    @BeforeEach
    public void beforeEach() {
        WireMock.reset();
    }
}
