package com.wix.mediaplatform.v6.service.live;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class CloseLiveStreamRequest extends MediaPlatformRequest {

    public CloseLiveStreamRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String streamId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/live/stream/" + streamId);
    }

}
