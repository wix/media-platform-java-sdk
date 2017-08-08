package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

public class CreateArchiveJob extends Job {

    public static final String job_type = "urn:job:archive.create";

    private CreateArchiveSpecification specification;

    private RestResponse result;

    @Override
    public CreateArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
