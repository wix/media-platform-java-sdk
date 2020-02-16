package com.wix.mediaplatform.v7.service.live;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class CloseLiveStreamRequest extends MediaPlatformRequest {

    public CloseLiveStreamRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String streamId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/live/stream/" + streamId, null);
    }

}
