package com.wix.mediaplatform.v7.service.archive;

import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.MediaPlatformResult;


public class CreateArchiveJobResult  extends MediaPlatformResult {

    private FileDescriptor createdFileDescriptor;

    public CreateArchiveJobResult() {
    }

    public FileDescriptor getCreatedFileDescriptor() {
        return createdFileDescriptor;
    }

}
