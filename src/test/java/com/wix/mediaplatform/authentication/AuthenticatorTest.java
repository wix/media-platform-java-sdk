package com.wix.mediaplatform.authentication;

import com.auth0.jwt.JWTVerifier;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.configuration.Configuration;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest extends BaseTest {

    private Configuration configuration = new Configuration("domain", "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private JWTVerifier verifier = new JWTVerifier(configuration.getSharedSecret());

    @Test
    public void getHeaderDefault() throws Exception {
        String header = authenticator.getHeader();

        Map<String, Object> claims = verifier.verify(header);
        assertThat(claims.get("iss").toString(), is("urn:app:appId"));
    }

    @Test
    public void getHeaderCustomToken() throws Exception {
        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .addVerb("verb");

        String header = authenticator.getHeader(token);

        Map<String, Object> claims = verifier.verify(header);
        assertThat(claims.get("iss").toString(), is("urn:app:appId"));
        assertThat(claims.get("aud").toString(), is("[verb]"));
    }
}