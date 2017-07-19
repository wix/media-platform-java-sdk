package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

public class CreateFileRequest {

    private String path;

    private String mimeType = "application/vnd.wix-media.dir";

    private FileDescriptor.Type type = FileDescriptor.Type.DIRECTORY;

    private long size;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    public CreateFileRequest() {
    }

    public CreateFileRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public CreateFileRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public CreateFileRequest setType(String type) {
        this.type = FileDescriptor.Type.fromString(type);
        return this;
    }

    public CreateFileRequest setType(FileDescriptor.Type type) {
        this.type = type;
        return this;
    }

    public CreateFileRequest setSize(long size) {
        this.size = size;
        return this;
    }

    public CreateFileRequest setAcl(String acl) {
        this.acl = FileDescriptor.Acl.fromString(acl);
        return this;
    }

    public CreateFileRequest setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }
}
