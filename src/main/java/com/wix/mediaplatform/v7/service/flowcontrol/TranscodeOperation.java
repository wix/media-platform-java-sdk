package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.service.transcode.TranscodeSpecification;

public class TranscodeOperation extends Operation {

    private TranscodeSpecification specification;

    @Override
    public TranscodeSpecification getSpecification() {
        return specification;
    }

    public TranscodeOperation setSpecification(TranscodeSpecification specification) {
        this.specification = specification;
        return this;
    }

    @Override
    public ExtraResults getExtraResults() {
        return null;
    }
}
