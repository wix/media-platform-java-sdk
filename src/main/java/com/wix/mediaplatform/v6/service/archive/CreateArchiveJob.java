package com.wix.mediaplatform.v6.service.archive;

import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;

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
