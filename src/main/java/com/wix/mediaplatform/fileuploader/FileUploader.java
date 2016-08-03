package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.authentication.AuthenticatedHttpClient;

public class FileUploader {

    private final AuthenticatedHttpClient client;

    public FileUploader(AuthenticatedHttpClient client) {
        this.client = client;
    }

    public String getUploadUrl() {

        return "";
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
