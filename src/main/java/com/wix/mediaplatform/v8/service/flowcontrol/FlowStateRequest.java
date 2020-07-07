package com.wix.mediaplatform.v8.service.flowcontrol;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class FlowStateRequest extends MediaPlatformRequest<FlowState> {

    FlowStateRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String flowId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/flow_control/flow/" + flowId, FlowState.class);
    }

}
