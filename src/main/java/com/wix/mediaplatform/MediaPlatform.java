package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.management.FileDownloader;
import com.wix.mediaplatform.management.FileManager;
import com.wix.mediaplatform.management.FileUploader;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class MediaPlatform {

    private final FileDownloader fileDownloader;

    private final FileManager fileManager;

    public MediaPlatform(String domain, String appId, String sharedSecret, HttpClient httpClient) {
        Configuration configuration = new Configuration(domain, appId, sharedSecret);
        Gson gson = getGson();

        Authenticator authenticator = new Authenticator(configuration);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);

        FileUploader fileUploader = new FileUploader(configuration, authenticatedHTTPClient);
        this.fileDownloader = new FileDownloader(configuration, authenticator);
        this.fileManager = new FileManager(configuration, authenticatedHTTPClient, fileUploader);
    }

    public MediaPlatform(String domain, String appId, String sharedSecret) {
        this(domain, appId, sharedSecret, getHttpClient());
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
