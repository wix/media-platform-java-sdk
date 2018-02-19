package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadUrlRequest {

    private String mimeType;

    private String path;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    public UploadUrlRequest() {
    }

    public UploadUrlRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public UploadUrlRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public UploadUrlRequest setAcl(String acl) {
        this.acl = FileDescriptor.Acl.fromString(acl);
        return this;
    }

    public UploadUrlRequest setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getPath() {
        return path;
    }

    public FileDescriptor.Acl getAcl() {
        return acl;
    }

    public Map<String, String> toParams() {
        Map<String, String> params = newHashMap();
        if (mimeType != null) {
            params.put("mimeType", mimeType);
        }
        if (path != null) {
            params.put("path", path);
        }
        if (acl != null) {
            params.put("acl", acl.getValue());
        }

        return params;
    }
}
