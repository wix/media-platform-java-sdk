package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.upload.UploadRequest;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUploader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final String uploadUrlEndpoint;

    public FileUploader(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {

        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl() throws IOException, UnauthorizedException, URISyntaxException {
        return authenticatedHTTPClient.get(uploadUrlEndpoint, null, GetUploadUrlResponse.class);
    }

    private <T> T uploadFile(String mimeType, String fileName, InputStream source, @Nullable UploadRequest uploadRequest, @Nullable Map<String, String> additionalParams, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl();

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setLaxMode();
        multipartEntityBuilder.setCharset(UTF_8);
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);
        multipartEntityBuilder.addTextBody("upload_token", uploadUrlResponse.getUploadToken());
        if (uploadRequest != null) {
            if (uploadRequest.getParentFolderId() != null) {
                multipartEntityBuilder.addTextBody("parent_folder_id", uploadRequest.getParentFolderId());
            }
            if (uploadRequest.getTags() != null && !uploadRequest.getTags().isEmpty()) {
                multipartEntityBuilder.addTextBody("tags", StringUtils.join(uploadRequest.getTags(), ","));
            }
        }
        if (additionalParams != null) {
            for (Map.Entry<String, String> entry : additionalParams.entrySet()) {
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity form = multipartEntityBuilder.build();

        return authenticatedHTTPClient.post(uploadUrlResponse.getUploadUrl(), form, responseType);
    }
}
