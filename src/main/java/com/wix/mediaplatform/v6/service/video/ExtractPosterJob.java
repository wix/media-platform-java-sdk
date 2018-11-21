package com.wix.mediaplatform.v6.service.video;

import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;
import com.wix.mediaplatform.v6.service.Specification;


public class ExtractPosterJob extends Job {

    private ExtractPosterSpecification specification;

    private RestResponse<FileDescriptor[]> result;

    @Override
    public Specification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
