package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ListFilesRequest;
import com.wix.mediaplatform.dto.request.UploadUrlRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.ListFilesResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.fileuploader.FileUploader;
import com.wix.mediaplatform.http.HTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.gson.Types.FILE_DESCRIPTOR_REST_RESPONSE;
import static com.wix.mediaplatform.gson.Types.FILE_LIST_REST_RESPONSE;

public class FileManager {

    private final HTTPClient httpClient;
    private final String baseUrl;
    private final FileUploader fileUploader;

    public FileManager(Configuration configuration, HTTPClient httpClient, FileUploader fileUploader) {
        this.httpClient = httpClient;

        this.baseUrl = "https://" + configuration.getDomain();

        this.fileUploader = fileUploader;
    }

    public GetUploadUrlResponse getUploadUrl() throws UnauthorizedException, IOException, URISyntaxException {
        return fileUploader.getUploadUrl(null);
    }

    public GetUploadUrlResponse getUploadUrl(UploadUrlRequest uploadUrlRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return fileUploader.getUploadUrl(uploadUrlRequest);
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, InputStream source) throws UnauthorizedException, IOException, URISyntaxException {
        return fileUploader.uploadFile(path, mimeType, fileName, source, null);
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, InputStream source, String acl) throws UnauthorizedException, IOException, URISyntaxException {
        return fileUploader.uploadFile(path, mimeType, fileName, source, acl);
    }

    public FileDescriptor createFile(FileDescriptor fileDescriptor) throws UnauthorizedException, IOException, URISyntaxException {
        return httpClient.post(baseUrl + "/files", fileDescriptor, null, FILE_DESCRIPTOR_REST_RESPONSE);
    }

    @Nullable
    public FileDescriptor getFile(String path) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        params.put("path", path);
        return httpClient.get(baseUrl + "/files", params, FILE_DESCRIPTOR_REST_RESPONSE);
    }

    @Nullable
    public FileDescriptor getFileMetadataById(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        return httpClient.get(baseUrl + "/files/" + fileId + "/metadata", null, FILE_DESCRIPTOR_REST_RESPONSE);
    }

    public ListFilesResponse listFiles(@Nullable ListFilesRequest listFilesRequest) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = null;
        if (listFilesRequest != null) {
                params = listFilesRequest.toParams();
        }

        return httpClient.get(baseUrl + "/files/ls_dir", params, FILE_LIST_REST_RESPONSE);
    }

    public void deleteFileByPath(String path) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        params.put("path", path);
        httpClient.delete(baseUrl + "/files", params, null);
    }

    public void deleteFileById(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        httpClient.delete(baseUrl + "/files/" + fileId, null, null);
    }
}
