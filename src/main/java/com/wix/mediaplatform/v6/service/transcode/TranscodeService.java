package com.wix.mediaplatform.v6.service.transcode;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformService;

public class TranscodeService extends MediaPlatformService {

    public TranscodeService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public TranscodeRequest transcodeRequest() {
        return new TranscodeRequest(authenticatedHTTPClient, baseUrl);
    }
}
