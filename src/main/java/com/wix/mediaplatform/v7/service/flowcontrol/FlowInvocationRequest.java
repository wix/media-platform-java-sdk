package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

import java.util.Map;

public class FlowInvocationRequest extends MediaPlatformRequest<FlowState> {

    private Invocation invocation;

    private Map<String, Component> flow;

    FlowInvocationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/flow_control/flow", FlowState.class);
    }


    public Invocation getInvocation() {
        return invocation;
    }

    public FlowInvocationRequest setInvocation(Invocation invocation) {
        this.invocation = invocation;
        return this;
    }

    public Map<String, Component> getFlow() {
        return flow;
    }

    public FlowInvocationRequest setFlow(Map<String, Component> flow) {
        this.flow = flow;
        return this;
    }
}
