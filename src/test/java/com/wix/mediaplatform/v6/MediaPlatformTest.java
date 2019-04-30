package com.wix.mediaplatform.v6;

import com.wix.mediaplatform.v6.auth.Token;
import com.wix.mediaplatform.v6.exception.InvalidConfigurationException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class MediaPlatformTest {

    @Test
    public void constructor() {
        new MediaPlatform("domain", "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullDomain() {
        new MediaPlatform(null, "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringDomain() {
        new MediaPlatform("", "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullAppId() {
        new MediaPlatform("domain", null, "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringAppId() {
        new MediaPlatform("domain", "", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullSharedSecret() {
        new MediaPlatform("domain", "appId", null);
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringSharedSecret() {
        new MediaPlatform("domain", "appId", "");
    }

    @Test
    public void getAuthorizationHeader() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", "appId",
                "95eee2c63ac2d15270628664c84f6ddd");

        Map<String, String> fileMatcher = newHashMap();
        fileMatcher.put("path", "/sites/3e001a2c-5da5-4e30-82c0-b44915e0e7af/*");

        List<Map<String, String>> andClauses = newArrayList();
        andClauses.add(fileMatcher);

        List<List<Map<String, String>>> objClaim = newArrayList();
        objClaim.add(andClauses);

        Token token = mediaPlatform.getToken()
                .addVerb("urn:service:file.upload")
                .setObject(objClaim);

        mediaPlatform.getAuthorizationHeader(token);
    }
}