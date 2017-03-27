package com.wix.mediaplatform.dto.request;

public class CreateFileRequest {

    private String path;

    private String mimeType = "application/vnd.wix-media.dir";

    private String type = "d";

    private long size;

    private String acl = "public";

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
        this.type = type;
        return this;
    }

    public CreateFileRequest setSize(long size) {
        this.size = size;
        return this;
    }

    public CreateFileRequest setAcl(String acl) {
        this.acl = acl;
        return this;
    }
}
