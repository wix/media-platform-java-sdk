package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;

public class ImportFileJob extends Job {

    public static final Job.Type job_type = Type.FILE_IMPORT;

    private ImportFileSpecification specification;

    private RestResponse<FileDescriptor> result;

    @Override
    public ImportFileSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse<FileDescriptor> getResult() {
        return result;
    }
}
