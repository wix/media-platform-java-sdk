package com.wix.mediaplatform.v8.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v8.auth.Authenticator;
import com.wix.mediaplatform.v8.configuration.Configuration;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformService;

public class FileService extends MediaPlatformService {

    private final ObjectMapper objectMapper;

    private final Authenticator authenticator;

    public FileService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient,
                       ObjectMapper objectMapper, Authenticator authenticator) {
        super(configuration, authenticatedHTTPClient);

        this.objectMapper = objectMapper;
        this.authenticator = authenticator;
    }

    public UploadConfigurationRequest uploadConfigurationRequest() {
        return new UploadConfigurationRequest(authenticatedHTTPClient, baseUrl);
    }

    public UploadFileRequest uploadFileRequest() {
        return new UploadFileRequest(authenticatedHTTPClient, baseUrl, objectMapper);
    }

    public ImportFileRequest importFileRequest() {
        return new ImportFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public CopyFileRequest copyFileRequest() {
        return new CopyFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public CreateFileRequest createFileRequest() {
        return new CreateFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public SignedDownloadUrlRequest signedUrlRequest() {
        return new SignedDownloadUrlRequest(authenticatedHTTPClient, baseUrl, authenticator, configuration.getAppId());
    }

    public FileRequest fileRequest() {
        return new FileRequest(authenticatedHTTPClient, baseUrl);
    }

    public FileMetadataRequest fileMetadataRequest() {
        return new FileMetadataRequest(authenticatedHTTPClient, baseUrl);
    }

    public FileIdMetadataRequest fileIdMetadataRequest(String fileId) {
        return new FileIdMetadataRequest(authenticatedHTTPClient, baseUrl, fileId);
    }

    public FileListRequest fileListRequest() {
        return new FileListRequest(authenticatedHTTPClient, baseUrl);
    }

    public DeleteFileRequest deleteFileRequest() {
        return new DeleteFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public DeleteFileIdRequest deleteFileIdRequest(String fileId) {
        return new DeleteFileIdRequest(authenticatedHTTPClient, baseUrl, fileId);
    }
}
