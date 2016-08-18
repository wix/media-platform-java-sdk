package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.FileBaseDTO;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.document.DocumentDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.video.VideoDTO;
import com.wix.mediaplatform.fileuploader.FileUploader;
import com.wix.mediaplatform.gson.RuntimeTypeAdapterFactory;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.management.FileManager;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class MediaPlatform {

    @SuppressWarnings("WeakerAccess")
    public final FileUploader fileUploader;

    @SuppressWarnings("WeakerAccess")
    public final FileManager fileManager;

    public MediaPlatform(String domain, String appId, String sharedSecret) {

        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        HttpClient httpClient = getHttpClient();
        Gson gson = getGson();

        AuthenticationFacade authenticationFacade = new AuthenticationFacade(configuration, httpClient, gson);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

        fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);
        fileManager = new FileManager(authenticatedHTTPClient, configuration);
    }

    public FileUploader fileUploader() {
        return fileUploader;
    }

    public FileManager fileManager() {
        return fileManager;
    }

    protected static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(FileBaseDTO.class, "media_type")
                        .registerSubtype(ImageDTO.class, "picture")
                        .registerSubtype(VideoDTO.class, "video")
                        .registerSubtype(AudioDTO.class, "music")
                        .registerSubtype(DocumentDTO.class, "document"))
                .create();
    }

    protected static HttpClient getHttpClient() {
        return HttpClients.createMinimal();
    }
}
