package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.filedownloader.FileDownloader;
import com.wix.mediaplatform.fileuploader.FileUploader;
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

    public MediaPlatform(String domain, String appId, String sharedSecret, HttpClient httpClient) {
        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        Gson gson = getGson();

        Authenticator authenticator = new Authenticator(configuration);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);

        fileUploader = new FileUploader(authenticatedHTTPClient, configuration);
        fileDownloader = new FileDownloader(authenticator, configuration);
        fileManager = new FileManager(authenticatedHTTPClient, configuration);
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

    public static Gson getGson() {
        return new GsonBuilder().create();
    }

    protected static HttpClient getHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(50);
        return HttpClients.createMinimal(connectionManager);
    }
}
