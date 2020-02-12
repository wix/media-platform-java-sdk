package com.wix.mediaplatform.v7.service.live;

import java.util.Map;

public class StateNotification {

    private String callbackUrl;

    private Map<String,String> customPayload;

    public StateNotification() {
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public StateNotification setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    public Map<String, String> getCustomPayload() {
        return customPayload;
    }

    public StateNotification setCustomPayload(Map<String, String> customPayload) {
        this.customPayload = customPayload;
        return this;
    }
}
