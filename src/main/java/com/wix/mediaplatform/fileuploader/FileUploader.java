package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.MediaType;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.fileuploader.dto.GetUploadUrlResponse;
import com.wix.mediaplatform.fileuploader.dto.UploadRequest;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;

public class FileUploader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final String uploadUrlEndpoint;

    public FileUploader(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {

        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(String userId) throws IOException, UnauthorizedException, URISyntaxException {
        return authenticatedHTTPClient.get(userId, uploadUrlEndpoint, null, GetUploadUrlResponse.class);
    }

    public void uploadImage(String userId, InputStream image, String fileName, UploadRequest uploadRequest) {

    }

    public void uploadAudio() {

    }

    public void uploadVideo() {

    }

    public void uploadDocument() {

    }

    private <T> T upload(String userId, MediaType mediaType, String mimeType, String fileName, InputStream source, UploadRequest uploadRequest, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {

        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(userId);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);
        multipartEntityBuilder.addTextBody("media_type", mediaType.getMediaType());
        multipartEntityBuilder.addTextBody("upload_token", uploadUrlResponse.getUploadToken());
        if (uploadRequest.getParentFolderId() != null) {
            multipartEntityBuilder.addTextBody("parent_folder_id", uploadRequest.getParentFolderId());
        }
        if (uploadRequest.getTags() != null && !uploadRequest.getTags().isEmpty()) {
            multipartEntityBuilder.addTextBody("tags", StringUtils.join(uploadRequest.getTags(), ","));
        }

        HttpEntity form = multipartEntityBuilder.build();

        return authenticatedHTTPClient.executeAnonymousMultipart(uploadUrlResponse.getUploadUrl(), form, responseType);
    }
}
