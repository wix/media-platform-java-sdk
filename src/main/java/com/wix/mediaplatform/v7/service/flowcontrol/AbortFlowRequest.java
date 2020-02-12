package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class AbortFlowRequest extends MediaPlatformRequest {

    AbortFlowRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String flowId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/flow_control/flow/" + flowId, null);
    }

}
