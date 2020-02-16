package com.wix.mediaplatform.v7.service.transcode;

import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformService;

public class TranscodeService extends MediaPlatformService {

    public TranscodeService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public TranscodeRequest transcodeRequest() {
        return new TranscodeRequest(authenticatedHTTPClient, baseUrl);
    }
}
