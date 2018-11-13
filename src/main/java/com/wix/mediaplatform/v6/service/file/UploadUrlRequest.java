package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

import static com.google.common.collect.Maps.newTreeMap;

public class UploadUrlRequest extends MediaPlatformRequest<UploadUrl> {

    private String mimeType;

    private String path;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    UploadUrlRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/_api/upload/url");
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

    protected Map<String, String> params() {

        Map<String, String> params = newTreeMap();

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
