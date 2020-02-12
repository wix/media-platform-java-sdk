package com.wix.mediaplatform.v7.service.video;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;
import com.wix.mediaplatform.v7.service.Source;

public class ExtractStoryboardsRequest extends MediaPlatformRequest<ExtractStoryboardJobGroup> {

    private Source[] sources;

    private ExtractStoryboardSpecification[] specifications;

    ExtractStoryboardsRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/av/storyboard", ExtractStoryboardJobGroup.class);
    }

    public Source[] getSources() {
        return sources;
    }

    public ExtractStoryboardsRequest setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public ExtractStoryboardSpecification[] getSpecifications() {
        return specifications;
    }

    public ExtractStoryboardsRequest setSpecifications(ExtractStoryboardSpecification[] specifications) {
        this.specifications = specifications;
        return this;
    }
}
