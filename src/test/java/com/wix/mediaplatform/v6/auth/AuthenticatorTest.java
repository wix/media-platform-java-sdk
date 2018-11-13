package com.wix.mediaplatform.v6.auth;

import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.configuration.Configuration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest extends BaseTest {

    private Configuration configuration = new Configuration("domain", "appId", "95eee2c63ac2d15270628664c84f6ddd");
    private Authenticator authenticator = new Authenticator(configuration);


    @Test
    public void getHeaderDefault() {
        String header = authenticator.getHeader();

        Token token = authenticator.decode(header);

        assertThat(token.getIssuer(), is("urn:app:appId"));
    }

    @Test
    public void getHeaderCustomToken() {
        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .addVerb("v1")
                .addVerb("v2");

        String header = authenticator.getHeader(token);

        Token decodedToken = authenticator.decode(header);
        assertThat(decodedToken.getIssuer(), is("urn:app:appId"));
        assertThat(decodedToken.getVerbs().get(0), is("v1"));
        assertThat(decodedToken.getVerbs().get(1), is("v2"));
    }
}