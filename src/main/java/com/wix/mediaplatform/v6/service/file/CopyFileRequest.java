package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;
import com.wix.mediaplatform.v6.service.Source;

public class CopyFileRequest extends MediaPlatformRequest<FileDescriptor> {

    private Source source;

    private Destination destination;

    CopyFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/copy/file");
    }

    public Source getSource() {
        return source;
    }

    public CopyFileRequest setSource(Source source) {
        this.source = source;
        return this;
    }

    public CopyFileRequest setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }
}