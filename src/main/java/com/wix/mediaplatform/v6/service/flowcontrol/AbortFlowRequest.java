package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class AbortFlowRequest extends MediaPlatformRequest {

    AbortFlowRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String flowId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/flow/" + flowId, null);
    }

}
