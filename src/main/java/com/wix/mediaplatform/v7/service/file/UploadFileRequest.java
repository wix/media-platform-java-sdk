package com.wix.mediaplatform.v7.service.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.FileLifecycle;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadFileRequest extends MediaPlatformRequest<FileDescriptor> {

    protected final String version;

    protected String path;

    protected byte[] content;

    protected File file;

    protected String mimeType = "application/octet-stream";

    protected FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    protected FileLifecycle lifecycle;

    @JsonIgnore
    protected ObjectMapper objectMapper;

    UploadFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        this(authenticatedHTTPClient, baseUrl, objectMapper, "v2");
    }

    UploadFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper, String version) {
        super(authenticatedHTTPClient, "POST", baseUrl, FileDescriptor.class);

        this.objectMapper = objectMapper;
        this.version = version;
    }

    @Override
    public FileDescriptor execute() throws MediaPlatformException {
        validate();

        UploadUrl uploadUrl = new UploadConfigurationRequest(authenticatedHTTPClient, url, version)
                .setPath(path)
                .setMimeType(mimeType)
                .setAcl(acl)
                .execute();

        Map<String, String> params = newHashMap();
        String uploadToken = uploadUrl.getUploadToken();
        if(uploadToken != null) {
            params.put("uploadToken", uploadToken);
        }

        if (lifecycle != null) {
            try {
                params.put("lifecycle", objectMapper.writeValueAsString(lifecycle));
            } catch (JsonProcessingException e) {
                throw new MediaPlatformException(e.getMessage());
            }
        }

        if (content != null) {
            return authenticatedHTTPClient.postForm(
                    uploadUrl.getUploadUrl(),
                    mimeType,
                    content,
                    params,
                    FileDescriptor.class);
        } else {
            return authenticatedHTTPClient.postForm(
                    uploadUrl.getUploadUrl(),
                    mimeType,
                    file,
                    params,
                    FileDescriptor.class);
        }
    }

    public byte[] getContent() {
        return content;
    }

    public UploadFileRequest setContent(byte[] content) {
        this.file = null;
        this.content = content;
        return this;
    }

    public UploadFileRequest setContent(File file) {
        return this.setFile(file);
    }

    public File getFile() {
        return file;
    }

    public UploadFileRequest setFile(File file) {
        this.content = null;
        this.file = file;
        return this;
    }

    public String getPath() {
        return path;
    }

    public UploadFileRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public FileLifecycle getLifecycle() {
        return lifecycle;
    }

    public UploadFileRequest setLifecycle(FileLifecycle lifecycle) {
        this.lifecycle = lifecycle;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public UploadFileRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public FileDescriptor.Acl getAcl() {
        return acl;
    }

    public UploadFileRequest setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }
}
