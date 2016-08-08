package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.fileuploader.FileUploader;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class MediaPlatform {

    @SuppressWarnings("WeakerAccess")
    public final FileUploader fileUploader;

    public MediaPlatform(String domain, String appId, String sharedSecret) {

        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        HttpClient httpClient = HttpClients.createMinimal();
        Gson gson = new GsonBuilder().create();

        AuthenticationFacade authenticationFacade = new AuthenticationFacade(configuration, httpClient, gson);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

        fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);
    }

    public FileUploader fileUploader() {
        return fileUploader;
    }
}
