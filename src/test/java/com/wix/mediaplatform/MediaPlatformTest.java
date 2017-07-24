package com.wix.mediaplatform;

import com.wix.mediaplatform.exception.InvalidConfigurationException;
import org.junit.Test;

public class MediaPlatformTest {

    @Test
    public void constructor() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullDomain() {
        MediaPlatform mediaPlatform = new MediaPlatform(null, "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringDomain() {
        MediaPlatform mediaPlatform = new MediaPlatform("", "appId", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullAppId() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", null, "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringAppId() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", "", "secret");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void nullSharedSecret() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", "appId", null);
    }

    @Test(expected = InvalidConfigurationException.class)
    public void emptyStringSharedSecret() {
        MediaPlatform mediaPlatform = new MediaPlatform("domain", "appId", "");
    }

}