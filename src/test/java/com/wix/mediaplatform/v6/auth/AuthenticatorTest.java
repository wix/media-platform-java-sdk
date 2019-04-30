package com.wix.mediaplatform.v6.auth;

import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.configuration.Configuration;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest extends BaseTest {

    private Configuration configuration = new Configuration("domain", "appId",
            "95eee2c63ac2d15270628664c84f6ddd");

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

    @Test
    public void getHeaderCustomTokenWithNestedObject() {

        //  [
        //      [
        //          {
        //              "path": "/sites/3e001a2c-5da5-4e30-82c0-b44915e0e7af/*"
        //          }
        //      ]
        //  ]
        Map<String, String> fileMatcher = newHashMap();
        fileMatcher.put("path", "/sites/3e001a2c-5da5-4e30-82c0-b44915e0e7af/*");

        List<Map<String, String>> andClauses = newArrayList();
        andClauses.add(fileMatcher);

        List<List<Map<String, String>>> objClaim = newArrayList();
        objClaim.add(andClauses);

        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .addVerb("urn:service:file.upload")
                .setObject(objClaim);


        String header = authenticator.getHeader(token);

        Token decodedToken = authenticator.decode(header);
        assertThat(decodedToken.getIssuer(), is("urn:app:appId"));
        assertThat(decodedToken.getVerbs().get(0), is("urn:service:file.upload"));
    }
}