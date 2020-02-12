package com.wix.mediaplatform.v7.service.live;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class LiveStreamRequest extends MediaPlatformRequest<LiveStream> {

    LiveStreamRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String streamId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/live/stream/" + streamId, LiveStream.class);
    }

}
