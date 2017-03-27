package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.request.CreateFileRequest;
import com.wix.mediaplatform.dto.request.ListFilesRequest;
import com.wix.mediaplatform.dto.request.UploadUrlRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.ListFilesResponse;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.gson.Types.*;

public class FileManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;
    private final String baseUrl;
    private final FileUploader fileUploader;

    public FileManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient, FileUploader fileUploader) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = "https://" + configuration.getDomain() + "/_api";

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

    public FileDescriptor createFile(CreateFileRequest createFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        RestResponse<FileDescriptor> restResponse = authenticatedHttpClient.post(
                baseUrl + "/files",
                createFileRequest,
                null,
                FILE_DESCRIPTOR_REST_RESPONSE);

        return restResponse.getPayload();
    }

    @Nullable
    public FileDescriptor getFile(String path) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        params.put("path", path);
        RestResponse<FileDescriptor> restResponse = authenticatedHttpClient.get(
                baseUrl + "/files",
                params,
                FILE_DESCRIPTOR_REST_RESPONSE);
        return restResponse.getPayload();
    }

    @Nullable
    public FileMetadata getFileMetadataById(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        RestResponse<FileMetadata> restResponse = authenticatedHttpClient.get(
                baseUrl + "/files/" + fileId + "/metadata",
                null,
                FILE_METADATA_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public ListFilesResponse listFiles(String path, @Nullable ListFilesRequest listFilesRequest) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        if (listFilesRequest != null) {
                params.putAll(listFilesRequest.toParams());
        }
        params.put("path", path);

        RestResponse<ListFilesResponse> restResponse = authenticatedHttpClient.get(
                baseUrl + "/files/ls_dir",
                params,
                FILE_LIST_REST_RESPONSE
        );
        return restResponse.getPayload();
    }

    public void deleteFileByPath(String path) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        params.put("path", path);
        authenticatedHttpClient.delete(baseUrl + "/files", params, null);
    }

    public void deleteFileById(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        authenticatedHttpClient.delete(baseUrl + "/files/" + fileId, null, null);
    }
}
