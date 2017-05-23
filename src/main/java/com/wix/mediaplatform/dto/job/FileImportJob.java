package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.response.RestResponse;

public class FileImportJob extends Job {

    private ImportFileSpecification specification;

    private RestResponse<FileDescriptor> result;

    @Override
    public Specification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
