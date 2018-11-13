package com.wix.mediaplatform.v6.service.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.FileLifecycle;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;
import com.wix.mediaplatform.v6.service.RestResponse;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadFileRequest extends MediaPlatformRequest<FileDescriptor> {

    private String path;

    private byte[] content;

    private String mimeType = "application/octet-stream";

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PUBLIC;

    private FileLifecycle lifecycle;

    @JsonIgnore
    private ObjectMapper objectMapper;

    UploadFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        super(authenticatedHTTPClient, "POST", baseUrl);

        this.objectMapper = objectMapper;
    }

    @Override
    public FileDescriptor execute() throws MediaPlatformException {

        validate();

        UploadUrl uploadUrl = new UploadUrlRequest(authenticatedHTTPClient, url)
                .setPath(path)
                .setMimeType(mimeType)
                .setAcl(acl)
                .execute();

        Map<String, String> params = newHashMap();
        params.put("path", path);
        params.put("uploadToken", uploadUrl.getUploadToken());
        params.put("mimeType", mimeType);
        params.put("acl", acl.getValue());

        if (lifecycle != null) {
            try {
                params.put("lifecycle", objectMapper.writeValueAsString(lifecycle));
            } catch (JsonProcessingException e) {
                throw new MediaPlatformException(e.getMessage());
            }
        }

        RestResponse<FileDescriptor[]> response = authenticatedHTTPClient.postForm(
                uploadUrl.getUploadUrl(),
                mimeType,
                content,
                params);
        return response.getPayload()[0];
    }

    public byte[] getContent() {
        return content;
    }

    public UploadFileRequest setContent(byte[] content) {
        this.content = content;
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
