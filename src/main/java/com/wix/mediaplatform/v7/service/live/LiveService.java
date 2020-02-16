package com.wix.mediaplatform.v7.service.live;

import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformService;

public class LiveService extends MediaPlatformService {

    public LiveService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public OpenLiveStreamRequest createLiveStreamRequest()  {
        return new OpenLiveStreamRequest(authenticatedHTTPClient, baseUrl);
    }

    public CloseLiveStreamRequest closeLiveStreamRequest(String streamId) {
        return new CloseLiveStreamRequest(authenticatedHTTPClient, baseUrl, streamId);
    }

    public LiveStreamRequest liveStreamRequest(String streamId) {
        return new LiveStreamRequest(authenticatedHTTPClient, baseUrl, streamId);
    }

    public LiveStreamListRequest liveStreamListRequest() {
        return new LiveStreamListRequest(authenticatedHTTPClient, baseUrl);
    }
}
