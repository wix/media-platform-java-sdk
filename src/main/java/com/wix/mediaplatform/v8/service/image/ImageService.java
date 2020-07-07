package com.wix.mediaplatform.v8.service.image;

import com.wix.mediaplatform.v8.auth.Authenticator;
import com.wix.mediaplatform.v8.auth.NS;
import com.wix.mediaplatform.v8.configuration.Configuration;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.image.ImageToken;
import com.wix.mediaplatform.v8.service.MediaPlatformService;

public class ImageService extends MediaPlatformService {

    private final Authenticator authenticator;

    public ImageService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient, Authenticator authenticator) {
        super(configuration, authenticatedHTTPClient);

        this.authenticator = authenticator;
    }

    public ExtractFeaturesRequest extractFeaturesRequest() {
        return new ExtractFeaturesRequest(authenticatedHTTPClient, baseUrl);
    }

    public ImageOperationRequest imageOperationRequest() {
        return new ImageOperationRequest(authenticatedHTTPClient, baseUrl);
    }

    public ImageToken newImageToken() {
        return (ImageToken) new ImageToken()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId());
    }

    public String signToken(ImageToken token) {
        return authenticator.encode(token);
    }
}
