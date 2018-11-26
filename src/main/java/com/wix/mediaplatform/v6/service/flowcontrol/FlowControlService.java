package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformService;

public class FlowControlService extends MediaPlatformService {

    public FlowControlService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public FlowInvocationRequest flowInvocationRequest() {
        return new FlowInvocationRequest(authenticatedHTTPClient, baseUrl);
    }

    public AbortFlowRequest abortFlowRequest(String flowId) {
        return new AbortFlowRequest(authenticatedHTTPClient, baseUrl, flowId);
    }

    public FlowStateRequest flowStateRequest(String flowId) {
        return new FlowStateRequest(authenticatedHTTPClient, baseUrl, flowId);
    }
}
