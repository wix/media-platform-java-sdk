package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.MediaType;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.fileuploader.dto.GetUploadUrlResponse;
import com.wix.mediaplatform.fileuploader.dto.UploadRequest;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.io.InputStream;
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

    public void uploadImage(String userId, InputStream image, UploadRequest uploadRequest) {

    }

    public void uploadAudio() {

    }

    public void uploadVideo() {

    }

    public void uploadDocument() {

    }

    private void upload(String userId, MediaType mediaType, InputStream source, UploadRequest uploadRequest) throws IOException, UnauthorizedException, URISyntaxException {

        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(userId);

        authenticatedHTTPClient.executeMultipart(source, uploadRequest);
    }
}
