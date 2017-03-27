package com.wix.mediaplatform.dto.metadata;

import com.wix.mediaplatform.dto.metadata.basic.BasicMetadata;
import com.wix.mediaplatform.dto.metadata.features.Features;

public class FileMetadata {

    private FileDescriptor fileDescriptor;

    private BasicMetadata basic;

    private Features features;

    public FileMetadata() {
    }

    public FileMetadata(FileDescriptor fileDescriptor, BasicMetadata basic, Features features) {
        this.fileDescriptor = fileDescriptor;
        this.basic = basic;
        this.features = features;
    }

    public FileDescriptor getFileDescriptor() {
        return fileDescriptor;
    }

    public BasicMetadata getBasic() {
        return basic;
    }

    public Features getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "FileMetadata{" +
                "fileDescriptor=" + fileDescriptor +
                ", basic=" + basic +
                ", features=" + features +
                '}';
    }
}
