package com.wix.mediaplatform.v7.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v7.auth.Authenticator;
import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformService;

public class FileService extends MediaPlatformService {

    private ObjectMapper objectMapper;

    private Authenticator authenticator;

    public FileService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient,
                       ObjectMapper objectMapper, Authenticator authenticator) {
        super(configuration, authenticatedHTTPClient);

        this.objectMapper = objectMapper;
        this.authenticator = authenticator;
    }

    @Deprecated
    public UploadUrlRequest uploadUrlRequest() {
        return new UploadUrlRequest(authenticatedHTTPClient, baseUrl);
    }

    public UploadFileRequest uploadFileRequest() {
        return new UploadFileRequest(authenticatedHTTPClient, baseUrl, objectMapper);
    }

    public UploadFileRequest uploadFileRequestV3() {
        return new UploadFileRequest(authenticatedHTTPClient, baseUrl, objectMapper, "v3");
    }

    public UploadConfigurationRequest uploadConfigurationRequest() {
        return new UploadConfigurationRequest(authenticatedHTTPClient, baseUrl);
    }

    public ImportFileRequest importFileRequest() {
        return new ImportFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public CopyFileRequest copyFileRequest() {
        return new CopyFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public CreateFileRequest createFileRequest()  {
        return new CreateFileRequest(authenticatedHTTPClient, baseUrl);
    }

    public DownloadUrlRequest downloadUrlRequest() {
        return new DownloadUrlRequest(authenticatedHTTPClient, baseUrl, authenticator, configuration.getAppId());
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
