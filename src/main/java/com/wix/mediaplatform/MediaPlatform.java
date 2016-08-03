package com.wix.mediaplatform;

import com.wix.mediaplatform.authentication.AuthenticatedHttpClient;
import com.wix.mediaplatform.configuration.Configuration;

public class MediaPlatform {


    public MediaPlatform(String domain, String appId, String sharedSecret) {

        Configuration configuration = new Configuration(domain, appId, sharedSecret);

        AuthenticatedHttpClient authenticatedHttpClient = new AuthenticatedHttpClient(configuration);

    }

}
