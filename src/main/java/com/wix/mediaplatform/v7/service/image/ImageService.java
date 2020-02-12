package com.wix.mediaplatform.v7.service.image;

import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformService;

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
