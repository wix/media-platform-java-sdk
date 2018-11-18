package com.wix.mediaplatform.v6.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;

import java.util.Map;

public abstract class MediaPlatformRequest<T> {

    @JsonIgnore
    protected AuthenticatedHTTPClient authenticatedHTTPClient;

    @JsonIgnore
    protected String url;

    @JsonIgnore
    private String method;

    protected MediaPlatformRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String method, String url) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.method = method;
        this.url = url;
    }

    public T execute() throws MediaPlatformException {

        validate();

        RestResponse<T> response;
        switch (method) {
            case "POST":
                response = authenticatedHTTPClient.post(url, this);
                return response.getPayload();
            case "GET":
                response = authenticatedHTTPClient.get(url, params());
                return response.getPayload();
            case "DELETE":
                response = authenticatedHTTPClient.delete(url, params());
                return response.getPayload();
            default:
                throw new MediaPlatformException("method not supported");
        }
    }

    // override this if you want to validate request prior to executing it
    protected void validate() throws MediaPlatformException {}

    // override this if you want to pass query params
    protected Map<String, String> params() {
        return null;
    }
}
