package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

public class ExtractArchiveJob extends Job {

    public static final String job_type = "urn:job:archive.extract";

    private ExtractArchiveSpecification specification;

    private RestResponse result;

    @Override
    public ExtractArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
