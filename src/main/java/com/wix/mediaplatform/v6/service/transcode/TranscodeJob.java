package com.wix.mediaplatform.v6.service.transcode;

import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;
import com.wix.mediaplatform.v6.service.Specification;

public class TranscodeJob extends Job {

    private TranscodeSpecification specification;

    private RestResponse<TranscodeJobResult> result;

    @Override
    public Specification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
