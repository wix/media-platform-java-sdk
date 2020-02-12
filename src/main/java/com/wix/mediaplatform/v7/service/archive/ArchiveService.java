package com.wix.mediaplatform.v7.service.archive;

import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformService;


public class ArchiveService extends MediaPlatformService {

    public ArchiveService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public CreateArchiveRequest createArchiveRequest() {
        return new CreateArchiveRequest(authenticatedHTTPClient, baseUrl);
    }

    public CreateArchiveManifestRequest createArchiveManifestRequest() {
        return new CreateArchiveManifestRequest(authenticatedHTTPClient, baseUrl);
    }

    public ExtractArchiveRequest extractArchiveRequest() {
        return new ExtractArchiveRequest(authenticatedHTTPClient, baseUrl);
    }
}
