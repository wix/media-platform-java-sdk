package com.wix.mediaplatform.v7.service.archive;

import com.wix.mediaplatform.v7.service.Job;
import com.wix.mediaplatform.v7.service.RestResponse;

public class ExtractArchiveJob extends Job {

    private ExtractArchiveSpecification specification;

    private RestResponse<ExtractArchiveJobResult> result;

    public ExtractArchiveJob() {
    }

    @Override
    public ExtractArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse<ExtractArchiveJobResult> getResult() {
        return result;
    }
}
