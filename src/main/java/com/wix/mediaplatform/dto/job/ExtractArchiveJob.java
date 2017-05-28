package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

public class ExtractArchiveJob extends Job {

    public static final String job_type = "urn:job:archive.extract";

    private ExtractArchiveSpecification specification;

    private RestResponse result;

    @Override
    public Specification getSpecification() {
        return null;
    }

    @Override
    public RestResponse getResult() {
        return null;
    }
}
