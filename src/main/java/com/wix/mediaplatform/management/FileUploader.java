package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ImportFileRequest;
import com.wix.mediaplatform.dto.request.UploadUrlRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.wix.mediaplatform.gson.Types.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUploader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String uploadUrlEndpoint;

    private final String apiBaseUrl;

    public FileUploader(Configuration configuration, AuthenticatedHTTPClient AuthenticatedHTTPClient) {

        this.authenticatedHTTPClient = AuthenticatedHTTPClient;

        this.apiBaseUrl = "https://" + configuration.getDomain() + "/_api";

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/_api/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(@Nullable UploadUrlRequest uploadUrlRequest) throws IOException, UnauthorizedException, URISyntaxException {
        Map<String, String> params = null;
        if (uploadUrlRequest != null) {
            params = uploadUrlRequest.toParams();
        }

        RestResponse<GetUploadUrlResponse> restResponse = authenticatedHTTPClient.get(
                uploadUrlEndpoint,
                params,
                GET_UPLOAD_URL_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, File source, @Nullable String acl) throws IOException, UnauthorizedException, URISyntaxException {
        UploadUrlRequest uploadUrlRequest = new UploadUrlRequest()
                .setMimeType(mimeType)
                .setPath(path);
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(uploadUrlRequest);

        MultipartEntityBuilder multipartEntityBuilder = prepareForm(path, mimeType, acl, uploadUrlResponse);
        multipartEntityBuilder.addBinaryBody(fileName, source);

        HttpEntity form = multipartEntityBuilder.build();

        RestResponse<FileDescriptor[]> restResponse = authenticatedHTTPClient.post(
                uploadUrlResponse.getUploadUrl(),
                form,
                FILE_DESCRIPTORS_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, InputStream source, @Nullable String acl) throws IOException, UnauthorizedException, URISyntaxException {
        UploadUrlRequest uploadUrlRequest = new UploadUrlRequest()
                .setMimeType(mimeType)
                .setPath(path);
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(uploadUrlRequest);

        MultipartEntityBuilder multipartEntityBuilder = prepareForm(path, mimeType, acl, uploadUrlResponse);
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);

        HttpEntity form = multipartEntityBuilder.build();

        RestResponse<FileDescriptor[]> restResponse = authenticatedHTTPClient.post(
                uploadUrlResponse.getUploadUrl(),
                form,
                FILE_DESCRIPTORS_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public Job importFile(ImportFileRequest importFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        RestResponse<Job> restResponse = authenticatedHTTPClient.post(
                apiBaseUrl + "/import/file",
                importFileRequest,
                null,
                JOB_REST_RESPONSE);

        return restResponse.getPayload();
    }

    private MultipartEntityBuilder prepareForm(String path, String mimeType, @Nullable String acl, GetUploadUrlResponse uploadUrlResponse) {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setLaxMode();
        multipartEntityBuilder.setCharset(UTF_8);
        multipartEntityBuilder.addTextBody("path", path);
        multipartEntityBuilder.addTextBody("uploadToken", uploadUrlResponse.getUploadToken());
        multipartEntityBuilder.addTextBody("mimeType", mimeType);
        if (acl != null) {
            multipartEntityBuilder.addTextBody("acl", acl);
        }
        return multipartEntityBuilder;
    }
}
