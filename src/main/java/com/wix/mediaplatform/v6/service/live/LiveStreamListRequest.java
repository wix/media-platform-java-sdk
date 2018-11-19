package com.wix.mediaplatform.v6.service.live;


import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

// todo: this API is wrong... replace... (pagination, filters, etc.)

public class LiveStreamListRequest extends MediaPlatformRequest<LiveStreamList> {

    LiveStreamListRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/live/list_streams", LiveStreamList.class);
    }

}
