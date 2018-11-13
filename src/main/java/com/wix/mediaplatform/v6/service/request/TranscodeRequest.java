package com.wix.mediaplatform.v6.service.request;

import com.wix.mediaplatform.v6.service.Source;
import com.wix.mediaplatform.v6.service.Specification;

import java.util.ArrayList;

public class TranscodeRequest {

    private ArrayList<Source> sources;

    private ArrayList<Specification> specifications;

    public TranscodeRequest() {
        sources = new ArrayList<Source>();
        specifications = new ArrayList<Specification>();
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public TranscodeRequest setSources(ArrayList<Source> sources) {
        this.sources = sources;
        return this;
    }

    public ArrayList<Specification> getSpecifications() {
        return specifications;
    }

    public TranscodeRequest setSpecifications(ArrayList<Specification> specifications) {
        this.specifications = specifications;
        return this;
    }

    public TranscodeRequest addSpecification(Specification specification) {
        this.specifications.add(specification);
        return this;
    }

    public TranscodeRequest addSource(Source source) {
        this.sources.add(source);
        return this;
    }

}