package com.wix.mediaplatform.v7.service.image;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;
import com.wix.mediaplatform.v7.service.Source;

public class ImageOperationRequest extends MediaPlatformRequest<FileDescriptor> {

    private Source source;

    private ImageOperationSpecification specification;

    ImageOperationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/images/operations", FileDescriptor.class);
    }

    public Source getSource() {
        return source;
    }

    public ImageOperationRequest setSource(Source source) {
        this.source = source;
        return this;
    }

    public ImageOperationSpecification getSpecification() {
        return specification;
    }

    public ImageOperationRequest setSpecification(ImageOperationSpecification specification) {
        this.specification = specification;
        return this;
    }
}
