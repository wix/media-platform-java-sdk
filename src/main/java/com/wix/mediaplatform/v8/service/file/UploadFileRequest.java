package com.wix.mediaplatform.v8.service.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v8.exception.MediaPlatformException;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.FileDescriptor;
import com.wix.mediaplatform.v8.service.FileLifecycle;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadFileRequest extends MediaPlatformRequest<FileDescriptor> {

    protected String path;

    protected byte[] content;

    protected File file;

    protected String mimeType = "application/octet-stream";

    protected FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    protected FileLifecycle lifecycle;

    @JsonIgnore
    protected ObjectMapper objectMapper;

    UploadFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        super(authenticatedHTTPClient, "POST", baseUrl, FileDescriptor.class);

        this.objectMapper = objectMapper;
    }

    @Override
    public FileDescriptor execute() throws MediaPlatformException {
        validate();

        UploadConfiguration uploadConfiguration = new UploadConfigurationRequest(authenticatedHTTPClient, url)
                .setPath(path)
                .setMimeType(mimeType)
                .setAcl(acl)
                .execute();

        Map<String, String> params = newHashMap();

        if (lifecycle != null) {
            try {
                params.put("lifecycle", objectMapper.writeValueAsString(lifecycle));
            } catch (JsonProcessingException e) {
                throw new MediaPlatformException(e.getMessage());
            }
        }

        if (content != null) {
            return authenticatedHTTPClient.postForm(
                    uploadConfiguration.getUploadUrl(),
                    mimeType,
                    content,
                    params,
                    FileDescriptor.class);
        } else {
            return authenticatedHTTPClient.postForm(
                    uploadConfiguration.getUploadUrl(),
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
