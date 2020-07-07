package com.wix.mediaplatform.v8.service.file;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.FileDescriptor;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class UploadConfigurationRequest extends MediaPlatformRequest<UploadConfiguration> {

    private String path;

    private String mimeType;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    UploadConfigurationRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/v3/upload/configuration", UploadConfiguration.class);
    }

    public UploadConfigurationRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public UploadConfigurationRequest setPath(String path) {
        this.path = path;
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
