package com.wix.mediaplatform.v7.service.file;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class CreateFileRequest extends MediaPlatformRequest<FileDescriptor> {

    private String path;

    private String mimeType = "application/vnd.wix-media.dir";

    private FileDescriptor.Type type = FileDescriptor.Type.DIRECTORY;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    private long size;

    public CreateFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/files", FileDescriptor.class);
    }

    public String getPath() {
        return path;
    }

    public CreateFileRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public CreateFileRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public FileDescriptor.Type getType() {
        return type;
    }

    public CreateFileRequest setType(FileDescriptor.Type type) {
        this.type = type;
        return this;
    }

    public long getSize() {
        return size;
    }

    public CreateFileRequest setSize(long size) {
        this.size = size;
        return this;
    }

    public FileDescriptor.Acl getAcl() {
        return acl;
    }

    public CreateFileRequest setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }
}
