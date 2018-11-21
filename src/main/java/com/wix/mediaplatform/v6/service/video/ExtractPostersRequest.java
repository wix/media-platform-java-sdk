package com.wix.mediaplatform.v6.service.video;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;
import com.wix.mediaplatform.v6.service.Source;

public class ExtractPostersRequest extends MediaPlatformRequest<ExtractPosterJobGroup> {

    private Source[] sources;

    private ExtractPosterSpecification[] specifications;

    ExtractPostersRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/av/poster", ExtractPosterJobGroup.class);
    }

    public Source[] getSources() {
        return sources;
    }

    public ExtractPostersRequest setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public ExtractPosterSpecification[] getSpecifications() {
        return specifications;
    }

    public ExtractPostersRequest setSpecifications(ExtractPosterSpecification[] specifications) {
        this.specifications = specifications;
        return this;
    }
}
