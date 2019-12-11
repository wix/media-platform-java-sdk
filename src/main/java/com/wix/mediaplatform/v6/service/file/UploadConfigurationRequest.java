package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class UploadConfigurationRequest extends MediaPlatformRequest<UploadUrl> {

    private String path;

    private String mimeType;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    UploadConfigurationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        this(authenticatedHTTPClient, baseUrl, "v2");
    }

    UploadConfigurationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String version) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/" + version + "/upload/configuration", UploadUrl.class);
    }

    public UploadConfigurationRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public UploadConfigurationRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public UploadConfigurationRequest setAcl(String acl) {
        this.acl = FileDescriptor.Acl.fromString(acl);
        return this;
    }

    public UploadConfigurationRequest setAcl(FileDescriptor.Acl acl) {
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
}
