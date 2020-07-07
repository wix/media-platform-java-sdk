package com.wix.mediaplatform.v8.service.flowcontrol;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class AbortFlowRequest extends MediaPlatformRequest {

    AbortFlowRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String flowId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/flow_control/flow/" + flowId, null);
    }

}
