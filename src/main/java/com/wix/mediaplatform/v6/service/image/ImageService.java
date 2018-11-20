package com.wix.mediaplatform.v6.service.image;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformService;

public class ImageService extends MediaPlatformService {

    public ImageService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public ExtractFeaturesRequest extractFeaturesRequest() {
        return new ExtractFeaturesRequest(authenticatedHTTPClient, baseUrl);
    }

    public ImageOperationRequest imageOperationRequest() {
        return new ImageOperationRequest(authenticatedHTTPClient, baseUrl);
    }
}
