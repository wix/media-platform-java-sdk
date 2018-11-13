package com.wix.mediaplatform.v6.service.archive;

import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;

public class ExtractArchiveJob extends Job {

    public static final Job.Type job_type = Type.ARCHIVE_EXTRACT;

    private ExtractArchiveSpecification specification;

    private RestResponse<ExtractArchiveJobResult> result;

    @Override
    public ExtractArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse<ExtractArchiveJobResult> getResult() { return result; }
}
