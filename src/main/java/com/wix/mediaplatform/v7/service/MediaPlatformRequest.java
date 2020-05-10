package com.wix.mediaplatform.v7.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;

import java.util.Map;

public abstract class MediaPlatformRequest<R> {

    @JsonIgnore
    protected AuthenticatedHTTPClient authenticatedHTTPClient;

    @JsonIgnore
    protected String url;

    @JsonIgnore
    private final String method;

    private final Class<R> clazz;

    protected MediaPlatformRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String method, String url, Class<R> clazz) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.method = method;
        this.url = url;

        this.clazz = clazz;
    }

    public R execute() throws MediaPlatformException {
        validate();

        switch (method) {
            case "POST":
                return authenticatedHTTPClient.post(url, this, clazz);
            case "GET":
                return authenticatedHTTPClient.get(url, params(), clazz);
            case "DELETE":
                return authenticatedHTTPClient.delete(url, params(), clazz);
            default:
                throw new MediaPlatformException("method not supported");
        }
    }

    // override this if you want to validate request prior to executing it
    @SuppressWarnings("RedundantThrows")
    protected void validate() throws MediaPlatformException {}

    // override this if you want to pass query params
    protected Map<String, String> params() {
        return null;
    }
}
