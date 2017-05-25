package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.response.RestResponse;

public class FileImportJob extends Job {

    public static final String job_type = "urn:job:import.file";

    private FileImportSpecification specification;

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
