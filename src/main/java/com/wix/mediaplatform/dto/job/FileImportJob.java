package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.response.RestResponse;

public class FileImportJob extends Job {

    public static final Job.Type job_type = Type.FILE_IMPORT;

    private FileImportSpecification specification;

    private RestResponse<FileDescriptor> result;

    @Override
    public FileImportSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
