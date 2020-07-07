package com.wix.mediaplatform.v8.service.video;

import com.wix.mediaplatform.v8.configuration.Configuration;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformService;

public class VideoService extends MediaPlatformService {

    public VideoService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public ExtractPostersRequest extractPostersRequest() {
        return new ExtractPostersRequest(authenticatedHTTPClient, baseUrl);
    }

    public ExtractStoryboardsRequest extractStoryboardsRequest() {
        return new ExtractStoryboardsRequest(authenticatedHTTPClient, baseUrl);
    }
}
