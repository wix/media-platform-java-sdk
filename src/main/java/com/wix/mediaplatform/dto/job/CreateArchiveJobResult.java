package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

/**
 * Created by alonne on 30/07/2017.
 */
public class CreateArchiveJobResult {
    private FileDescriptor createdFileDescriptor;

    public CreateArchiveJobResult() {
    }

    public FileDescriptor getCreatedFileDescriptor() {
        return createdFileDescriptor;
    }

    @Override
    public String toString() {
        return "CreateArchiveJobResult{" +
                "createdFileDescriptor=" + createdFileDescriptor +
                '}';
    }
}
