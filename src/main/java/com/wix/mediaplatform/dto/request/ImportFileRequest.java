package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.job.Destination;

public class ImportFileRequest {

    private String sourceUrl;

    private ExternalAuthorization externalAuthorization;

    private Destination destination;

    public ImportFileRequest() {
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
}