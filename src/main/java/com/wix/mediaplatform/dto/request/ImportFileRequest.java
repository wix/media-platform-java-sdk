package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.job.Destination;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportFileRequest that = (ImportFileRequest) o;
        return Objects.equals(sourceUrl, that.sourceUrl) &&
                Objects.equals(externalAuthorization, that.externalAuthorization) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceUrl, externalAuthorization, destination);
    }
}