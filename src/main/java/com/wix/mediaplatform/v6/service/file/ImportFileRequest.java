package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class ImportFileRequest extends MediaPlatformRequest<ImportFileJob> {

    private String sourceUrl;

    private ExternalAuthorization externalAuthorization;

    private Destination destination;

    ImportFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/import/file");
    }

    public ImportFileRequest setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    public ImportFileRequest setExternalAuthorization(ExternalAuthorization externalAuthorization) {
        this.externalAuthorization = externalAuthorization;
        return this;
    }

    public ImportFileRequest setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public ExternalAuthorization getExternalAuthorization() {
        return externalAuthorization;
    }

    public Destination getDestination() {
        return destination;
    }
}