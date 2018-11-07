package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.*;
import com.wix.mediaplatform.dto.lifecycle.Lifecycle;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.gson.*;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.http.RetryErrorsStrategy;
import com.wix.mediaplatform.management.*;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class MediaPlatform {

    public static final int MAX_RETRIES = 5;
    public static final int RETRY_INTERVAL_MILLISECONDS = 500;
    private final FileDownloader fileDownloader;
    private final FileManager fileManager;
    private final JobManager jobManager;
    private final ArchiveManager archiveManager;
    private final TranscodeManager transcodeManager;

    public MediaPlatform(Configuration configuration) {
        Gson gson = getGson(false);
        Authenticator authenticator = new Authenticator(configuration);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, getHttpClient(),
                gson);

        FileUploader fileUploader = new FileUploader(configuration, authenticatedHTTPClient, gson);
        this.fileDownloader = new FileDownloader(configuration, authenticator);
        this.fileManager = new FileManager(configuration, authenticatedHTTPClient, fileUploader);
        this.jobManager = new JobManager(configuration, authenticatedHTTPClient);
        this.archiveManager = new ArchiveManager(configuration, authenticatedHTTPClient);
        this.transcodeManager = new TranscodeManager(configuration, authenticatedHTTPClient);
    }

    public MediaPlatform(String domain, String appId, String sharedSecret, HttpClient httpClient) {
        Configuration configuration = new Configuration(domain, appId, sharedSecret);

        Gson gson = getGson(false);
        Authenticator authenticator = new Authenticator(configuration);
        AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);

        FileUploader fileUploader = new FileUploader(configuration, authenticatedHTTPClient, gson);
        this.fileDownloader = new FileDownloader(configuration, authenticator);
        this.fileManager = new FileManager(configuration, authenticatedHTTPClient, fileUploader);
        this.jobManager = new JobManager(configuration, authenticatedHTTPClient);
        this.archiveManager = new ArchiveManager(configuration, authenticatedHTTPClient);
        this.transcodeManager = new TranscodeManager(configuration, authenticatedHTTPClient);
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

    public JobManager jobManager() {
        return jobManager;
    }

    public TranscodeManager transcodeManager() {
        return transcodeManager;
    }

    public ArchiveManager archiveManager() {
        return archiveManager;
    }

    public static Gson getGson(boolean pretty) {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(FileMetadata.class, new FileMetadataJsonDeserializer())
                .registerTypeAdapter(FileDescriptor.Acl.class, new FileAclJsonDeserializer())
                .registerTypeAdapter(FileDescriptor.Acl.class, new FileAclJsonSerializer())
                .registerTypeAdapter(FileDescriptor.Type.class, new FileTypeJsonDeserializer())
                .registerTypeAdapter(FileDescriptor.Type.class, new FileTypeJsonSerializer())
                .registerTypeAdapter(Lifecycle.Action.class, new LifecycleActionJsonDeserializer())
                .registerTypeAdapter(Lifecycle.Action.class, new LifecycleActionJsonSerializer())
                .registerTypeAdapter(Job.Type.class, new JobTypeJsonDeserializer())
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Job.class, "type")
                        .registerSubtype(FileImportJob.class, FileImportJob.job_type.getValue())
                        .registerSubtype(ExtractArchiveJob.class, ExtractArchiveJob.job_type.getValue())
                        .registerSubtype(CreateArchiveJob.class, CreateArchiveJob.job_type.getValue())
                        .registerSubtype(TranscodeJob.class, TranscodeJob.job_type.getValue()));

        if (pretty) {
            builder.setPrettyPrinting();
        }

        return builder.create();
    }

    public static HttpClient getHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(50);

        return getRetryingHttpClientBuilder().setConnectionManager(connectionManager).build();
    }

    public static HttpClientBuilder getRetryingHttpClientBuilder() {
        RetryErrorsStrategy retryErrorsStrategy = new RetryErrorsStrategy(MAX_RETRIES, RETRY_INTERVAL_MILLISECONDS);
        return HttpClientBuilder.create().setServiceUnavailableRetryStrategy(retryErrorsStrategy);
    }
}
