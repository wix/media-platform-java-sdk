package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.collection.CollectionManager;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.FileDTO;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.document.DocumentDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.video.VideoDTO;
import com.wix.mediaplatform.filedownloader.FileDownloader;
import com.wix.mediaplatform.fileuploader.FileUploader;
import com.wix.mediaplatform.gson.RuntimeTypeAdapterFactory;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.management.FileManager;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class MediaPlatform {

    @SuppressWarnings("WeakerAccess")
    public final FileUploader fileUploader;

    @SuppressWarnings("WeakerAccess")
    public final FileDownloader fileDownloader;

    @SuppressWarnings("WeakerAccess")
    public final FileManager fileManager;

    @SuppressWarnings("WeakerAccess")
    public final CollectionManager collectionManager;

    public MediaPlatform(String domain, String appId, String sharedSecret, HttpClient httpClient) {
        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        Gson gson = getGson();

        AuthenticationFacade authenticationFacade = new AuthenticationFacade(configuration, httpClient, gson);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

        fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);
        fileDownloader = new FileDownloader(authenticatedHTTPClient, configuration);
        fileManager = new FileManager(authenticatedHTTPClient, configuration);
        collectionManager = new CollectionManager(authenticatedHTTPClient, configuration);
    }

    public MediaPlatform(String domain, String appId, String sharedSecret) {
        this(domain, appId, sharedSecret, getHttpClient());
    }

    public FileUploader fileUploader() {
        return fileUploader;
    }

    public FileDownloader fileDownloader() {
        return fileDownloader;
    }

    public FileManager fileManager() {
        return fileManager;
    }

    public CollectionManager collectionManager() {
        return collectionManager;
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(FileDTO.class, "media_type")
                        .registerSubtype(ImageDTO.class, "picture")
                        .registerSubtype(ImageDTO.class, "secure_picture")
                        .registerSubtype(VideoDTO.class, "video")
                        .registerSubtype(VideoDTO.class, "secure_video")
                        .registerSubtype(AudioDTO.class, "music")
                        .registerSubtype(AudioDTO.class, "secure_music")
                        .registerSubtype(DocumentDTO.class, "document")
                        .registerSubtype(DocumentDTO.class, "secure_document"))
                .create();
    }

    protected static HttpClient getHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(50);
        return HttpClients.createMinimal(connectionManager);
    }
}
