package com.wix.mediaplatform.v8.service.archive;

import com.wix.mediaplatform.v8.service.FileDescriptor;
import com.wix.mediaplatform.v8.service.MediaPlatformResult;


public class CreateArchiveJobResult  extends MediaPlatformResult {

    private FileDescriptor createdFileDescriptor;

    public CreateArchiveJobResult() {
    }

    public FileDescriptor getCreatedFileDescriptor() {
        return createdFileDescriptor;
    }

}
