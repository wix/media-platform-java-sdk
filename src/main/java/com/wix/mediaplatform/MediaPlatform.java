package com.wix.mediaplatform;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.fileuploader.FileUploader;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import okhttp3.OkHttpClient;

public class MediaPlatform {

    @SuppressWarnings("WeakerAccess")
    public final FileUploader fileUploader;

    public MediaPlatform(String domain, String appId, String sharedSecret) {

        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        AuthenticationFacade authenticationFacade = new AuthenticationFacade(configuration, httpClient, gson);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

        fileUploader = new FileUploader(authenticatedHTTPClient, configuration);
    }

    public FileUploader fileUploader() {
        return fileUploader;
    }
}
