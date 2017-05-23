package com.wix.mediaplatform.dto.request;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ExternalAuthorization {

    private Map<String, String> headers = newHashMap();

    public ExternalAuthorization() {
    }

    public ExternalAuthorization setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public ExternalAuthorization addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }
}
