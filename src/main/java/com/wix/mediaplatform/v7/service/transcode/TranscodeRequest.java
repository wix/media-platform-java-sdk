package com.wix.mediaplatform.v7.service.transcode;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;
import com.wix.mediaplatform.v7.service.Source;

import java.util.ArrayList;
import java.util.List;

public class TranscodeRequest extends MediaPlatformRequest<TranscodeJobGroup> {

    private List<Source> sources = new ArrayList<>();

    private List<TranscodeSpecification> specifications = new ArrayList<>();

    public TranscodeRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/av/transcode", TranscodeJobGroup.class);
    }

    public List<Source> getSources() {
        return sources;
    }

    public TranscodeRequest setSources(List<Source> sources) {
        this.sources = sources;
        return this;
    }

    public List<TranscodeSpecification> getSpecifications() {
        return specifications;
    }

    public TranscodeRequest setSpecifications(List<TranscodeSpecification> specifications) {
        this.specifications = specifications;
        return this;
    }

    public TranscodeRequest addSpecification(TranscodeSpecification specification) {
        this.specifications.add(specification);
        return this;
    }

    public TranscodeRequest addSource(Source source) {
        this.sources.add(source);
        return this;
    }
}
