package com.wix.mediaplatform.v8.service;

import com.wix.mediaplatform.v8.configuration.Configuration;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;

public abstract class MediaPlatformService {

    protected final Configuration configuration;

    protected final AuthenticatedHTTPClient authenticatedHTTPClient;

    protected final String baseUrl;

    public MediaPlatformService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        this.configuration = configuration;

        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.baseUrl = configuration.getBaseUrl() + "/_api";
    }
}
