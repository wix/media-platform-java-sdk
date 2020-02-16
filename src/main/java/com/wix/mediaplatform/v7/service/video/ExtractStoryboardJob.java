package com.wix.mediaplatform.v7.service.video;

import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.Job;
import com.wix.mediaplatform.v7.service.RestResponse;
import com.wix.mediaplatform.v7.service.Specification;


public class ExtractStoryboardJob extends Job {

    private ExtractStoryboardSpecification specification;

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
