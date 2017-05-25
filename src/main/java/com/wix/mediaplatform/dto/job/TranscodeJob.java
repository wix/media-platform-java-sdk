package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

public class TranscodeJob extends Job {

    public static final String job_type = "urn:job:av.transcode";

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
