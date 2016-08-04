package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.fileuploader.dto.GetUploadUrlResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;

public class FileUploader {

    private final AuthenticatedHTTPClient httpClient;
    private final String uploadUrlEndpoint;

    public FileUploader(AuthenticatedHTTPClient httpClient, Configuration configuration) {

        this.httpClient = httpClient;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(String userId) throws IOException, UnauthorizedException {
        return httpClient.execute("GET", userId, uploadUrlEndpoint, null, GetUploadUrlResponse.class);
    }

    public void uploadImage() {

    }

    public void uploadAudio() {

    }

    public void uploadVideo() {

    }

    public void uploadDocument() {

    }


}
