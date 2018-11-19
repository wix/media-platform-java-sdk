package com.wix.mediaplatform.v6;

import com.wix.mediaplatform.v6.exception.InvalidConfigurationException;
import org.junit.Test;

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

}