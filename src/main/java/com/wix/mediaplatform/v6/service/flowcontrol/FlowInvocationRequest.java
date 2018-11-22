package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

public class FlowInvocationRequest extends MediaPlatformRequest<FlowState> {

    private Invocation invocation;

    private Map<String, Component> flow;

    FlowInvocationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/flow_control", FlowState.class);
    }


}
