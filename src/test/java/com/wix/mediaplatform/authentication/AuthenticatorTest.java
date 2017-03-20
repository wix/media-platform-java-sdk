package com.wix.mediaplatform.authentication;

import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.configuration.Configuration;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest extends BaseTest {

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);

    @Test
    public void getHeaderDefault() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-auth-token-response.json")));

        String token = authenticator.getHeader();
        assertThat(token, is("MCLOUDTOKEN token"));
    }

    @Test
    public void getHeaderCustomToken() throws Exception {
        stubFor(get(urlEqualTo("/apps/auth/token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-auth-token-response.json")));

        String token = authenticator.getHeader();
        assertThat(token, is("MCLOUDTOKEN token"));
    }
}