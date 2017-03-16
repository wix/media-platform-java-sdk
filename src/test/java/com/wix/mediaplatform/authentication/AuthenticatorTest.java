package com.wix.mediaplatform.authentication;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.configuration.Configuration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration, httpClient, gson);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getHeader() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-auth-token-response.json")));

        String token = authenticator.getHeader("userId");
        assertThat(token, is("MCLOUDTOKEN token"));
    }

    @Test
    public void getHeaderCached() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-auth-token-response.json")));

        String token = authenticator.getHeader("userId");
        assertThat(token, is("MCLOUDTOKEN token"));
        token = authenticator.getHeader("userId");
        assertThat(token, is("MCLOUDTOKEN token"));

        verify(exactly(1), getRequestedFor(urlEqualTo("/apps/auth/token")));
    }

    @Test(expected = IOException.class)
    public void getHeaderBadResponse() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withStatus(500)));

        authenticator.getHeader("userId");
    }

    @Test(expected = IOException.class)
    public void getHeaderMalformedResponse() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("Not a JSON")));

        authenticator.getHeader("userId");
    }

    @Test
    public void invalidateToken() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-auth-token-response.json")));
        authenticator.getHeader("userId");

        authenticator.invalidateToken("userId");
        authenticator.getHeader("userId");

        verify(exactly(2), getRequestedFor(urlEqualTo("/apps/auth/token")));
    }

    @Test
    public void invalidateTokenIdempotent() throws Exception {
        authenticator.invalidateToken("moshe");
    }
}