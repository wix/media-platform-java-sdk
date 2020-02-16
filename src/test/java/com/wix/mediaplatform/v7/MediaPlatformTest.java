package com.wix.mediaplatform.v7;

import com.wix.mediaplatform.v7.auth.Token;
import com.wix.mediaplatform.v7.exception.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MediaPlatformTest {

    @Test
    public void constructor() {
        new MediaPlatform("domain", "appId", "secret");
    }

    @Test
    public void nullDomain() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform(null, "appId", "secret")
        );
    }

    @Test
    public void emptyStringDomain() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform("", "appId", "secret")
        );
    }

    @Test
    public void nullAppId() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform("domain", null, "secret")
        );
    }

    @Test
    public void emptyStringAppId() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform("domain", "", "secret")
        );
    }

    @Test
    public void nullSharedSecret() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform("domain", "appId", null)
        );
    }

    @Test
    public void emptyStringSharedSecret() {
        assertThrows(InvalidConfigurationException.class, () ->
                new MediaPlatform("domain", "appId", "")
        );
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