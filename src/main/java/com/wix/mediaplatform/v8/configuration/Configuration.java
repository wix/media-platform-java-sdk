package com.wix.mediaplatform.v8.configuration;

import com.wix.mediaplatform.v8.exception.InvalidConfigurationException;

public class Configuration {

    private final String domain;

    private final String appId;

    private final String sharedSecret;

    private final String baseUrl;

    public Configuration(String domain, String appId, String sharedSecret) {
        this("https", domain, appId, sharedSecret);
    }

    public Configuration(String scheme, String domain, String appId, String sharedSecret) {
        validateDomain(domain);
        validateAppId(appId);
        validateSecret(sharedSecret);

        this.domain = domain;
        this.appId = appId;
        this.sharedSecret = sharedSecret;
        this.baseUrl = scheme + "://" + domain;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    private void validateDomain(String domain) {
        if (domain == null || domain.isEmpty()) {
            throw new InvalidConfigurationException("domain is null or empty");
        }
    }

    private void validateAppId(String appId) {
        if (appId == null || appId.isEmpty()) {
            throw new InvalidConfigurationException("appId is null or empty");
        }
    }

    private void validateSecret(String sharedSecret) {
        if (sharedSecret == null || sharedSecret.isEmpty()) {
            throw new InvalidConfigurationException("sharedSecret is null or empty");
        }
    }
}
