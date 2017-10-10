package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

public class CreateArchiveJob extends Job {

    public static final Job.Type job_type = Type.ARCHIVE_CREATE;

    private CreateArchiveSpecification specification;

    private RestResponse<CreateArchiveJobResult> result;

    @Override
    public CreateArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
