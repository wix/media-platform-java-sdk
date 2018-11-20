package com.wix.mediaplatform.v6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public abstract class BaseTest {

    private int PORT = 8443;

    private OkHttpClient httpClient = new OkHttpClient();

    protected ObjectMapper objectMapper = MediaPlatform.getMapper();

    protected Configuration configuration = new Configuration("http", "localhost:" + PORT,
            "appId",
            "95eee2c63ac2d15270628664c84f6ddd");

    protected Authenticator authenticator = new Authenticator(configuration);

    protected AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient,
            objectMapper);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(PORT));

    @Before
    public void setup() {
        WireMock.reset();
    }
}
