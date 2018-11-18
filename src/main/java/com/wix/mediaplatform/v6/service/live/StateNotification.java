package com.wix.mediaplatform.v6.service.live;

import java.util.HashMap;

public class StateNotification {
    private String callbackUrl;

    private HashMap<String,String> customPayload;

    public StateNotification() {
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public StateNotification setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    public HashMap<String, String> getCustomPayload() {
        return customPayload;
    }

    public StateNotification setCustomPayload(HashMap<String, String> customPayload) {
        this.customPayload = customPayload;
        return this;
    }
}
