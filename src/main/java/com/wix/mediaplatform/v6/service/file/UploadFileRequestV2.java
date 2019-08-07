package com.wix.mediaplatform.v6.service.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.FileLifecycle;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadFileRequestV2 extends MediaPlatformRequest<FileDescriptor> {

    private String path;

    private byte[] content;

    private File file;

    private String mimeType = "application/octet-stream";

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    private FileLifecycle lifecycle;

    @JsonIgnore
    private ObjectMapper objectMapper;

    UploadFileRequestV2(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        super(authenticatedHTTPClient, "POST", baseUrl, FileDescriptor.class);

        this.objectMapper = objectMapper;
    }

    @Override
    public FileDescriptor execute() throws MediaPlatformException {
        validate();

        UploadUrl uploadUrl = new UploadConfigurationRequest(authenticatedHTTPClient, url)
                .setPath(path)
                .setMimeType(mimeType)
                .setAcl(acl)
                .execute();

        Map<String, String> params = newHashMap();
        params.put("uploadToken", uploadUrl.getUploadToken());

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

    public UploadFileRequestV2 setContent(byte[] content) {
        this.file = null;
        this.content = content;
        return this;
    }

    public UploadFileRequestV2 setContent(File file) {
        return this.setFile(file);
    }

    public File getFile() {
        return file;
    }

    public UploadFileRequestV2 setFile(File file) {
        this.content = null;
        this.file = file;
        return this;
    }

    public String getPath() {
        return path;
    }

    public UploadFileRequestV2 setPath(String path) {
        this.path = path;
        return this;
    }

    public FileLifecycle getLifecycle() {
        return lifecycle;
    }

    public UploadFileRequestV2 setLifecycle(FileLifecycle lifecycle) {
        this.lifecycle = lifecycle;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public UploadFileRequestV2 setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public FileDescriptor.Acl getAcl() {
        return acl;
    }

    public UploadFileRequestV2 setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }
}
