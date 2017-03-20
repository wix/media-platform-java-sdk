package com.wix.mediaplatform.dto.request;

public class UploadRequest {

    private String mimeType = "application/octet-stream";

    private String acl = "public";

    public UploadRequest() {
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getAcl() {
        return acl;
    }

    public UploadRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public UploadRequest setAcl(String acl) {
        this.acl = acl;
        return this;
    }
}
