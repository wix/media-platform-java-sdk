package com.wix.mediaplatform.v8.service;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Callback {
    private String url;
    private Map<String, Object> attachment = newHashMap();
    private Map<String, String> headers = newHashMap();
    private boolean passthrough;

    public Callback() {
    }

    public String getUrl() {
        return url;
    }

    public Callback setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public Callback setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
        return this;
    }

    public Callback addAttachment(String key, Object value) {
        this.attachment.put(key, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Callback setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Callback addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public boolean isPassthrough() {
        return passthrough;
    }

    public Callback setPassthrough(boolean passthrough) {
        this.passthrough = passthrough;
        return this;
    }
}
