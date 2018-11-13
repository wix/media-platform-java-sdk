package com.wix.mediaplatform.v6.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.gson.Types;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformService;
import com.wix.mediaplatform.v6.service.RestResponse;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newTreeMap;

public class FileService extends MediaPlatformService {

    private ObjectMapper objectMapper;

    private Authenticator authenticator;

    public FileService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient,
                       ObjectMapper objectMapper, Authenticator authenticator) {
        super(configuration, authenticatedHTTPClient);

        this.objectMapper = objectMapper;
        this.authenticator = authenticator;
    }

    public UploadUrlRequest uploadUrlRequest() {
        return new UploadUrlRequest(authenticatedHTTPClient, baseUrl);
    }

    public UploadFileRequest uploadFileRequest() {
        return new UploadFileRequest(authenticatedHTTPClient, baseUrl, objectMapper);
    }

    public ImportFileRequest importFileRequest() {
        return new ImportFileRequest(authenticatedHTTPClient, baseUrl);
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

    public FileList listFiles(String path, @Nullable FileListRequest fileListRequest) throws MediaPlatformException, IOException, URISyntaxException {
        Map<String, String> params = newTreeMap();
        if (fileListRequest != null) {
                params.putAll(fileListRequest.toParams());
        }
        params.put("path", path);

        RestResponse<FileList> restResponse = authenticatedHttpClient.get(
                baseUrl + "/files/ls_dir",
                params,
                Types.FILE_LIST_REST_RESPONSE
        );
        return restResponse.getPayload();
    }

    public void deleteFileByPath(String path) throws MediaPlatformException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        params.put("path", path);
        authenticatedHttpClient.delete(baseUrl + "/files", params, null);
    }

    public void deleteFileById(String fileId) throws MediaPlatformException, IOException, URISyntaxException {
        authenticatedHttpClient.delete(baseUrl + "/files/" + fileId, null, null);
    }
}
