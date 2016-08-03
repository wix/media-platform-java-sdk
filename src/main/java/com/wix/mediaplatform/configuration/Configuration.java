package com.wix.mediaplatform.configuration;

public class Configuration {

    private final String domain;

    private final String appId;

    private final String sharedSecret;

    public Configuration(String domain, String appId, String sharedSecret) {
        this.domain = domain;
        this.appId = appId;
        this.sharedSecret = sharedSecret;
    }

    public String getDomain() {
        return domain;
    }

    public String getAppId() {
        return appId;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }
}
