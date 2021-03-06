package com.wix.mediaplatform.v8.service.flowcontrol;

import com.wix.mediaplatform.v8.service.transcode.TranscodeSpecification;

public class TranscodeComponent extends Component {

    private TranscodeSpecification specification;

    @Override
    public TranscodeSpecification getSpecification() {
        return specification;
    }

    public TranscodeComponent setSpecification(TranscodeSpecification specification) {
        this.specification = specification;
        return this;
    }
}
