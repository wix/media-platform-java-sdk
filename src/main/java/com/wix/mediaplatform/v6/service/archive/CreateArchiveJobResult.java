package com.wix.mediaplatform.v6.service.archive;

import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformResult;


public class CreateArchiveJobResult  extends MediaPlatformResult {

    private FileDescriptor createdFileDescriptor;

    public CreateArchiveJobResult() {
    }

    public FileDescriptor getCreatedFileDescriptor() {
        return createdFileDescriptor;
    }

}
