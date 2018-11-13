package com.wix.mediaplatform.v6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.management.JobManager;
import com.wix.mediaplatform.v6.management.TranscodeManager;
import com.wix.mediaplatform.v6.service.archive.ArchiveService;
import com.wix.mediaplatform.v6.service.file.FileDownloader;
import com.wix.mediaplatform.v6.service.file.FileService;
import com.wix.mediaplatform.v6.service.file.FileUploader;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;

public class MediaPlatform {

    private final FileDownloader fileDownloader;
    private final FileService fileService;
    private final JobManager jobManager;
    private final ArchiveService archiveService;
    private final TranscodeManager transcodeManager;

    public MediaPlatform(String domain, String appId, String sharedSecret) {
        this(domain, appId, sharedSecret, null);
    }

    public MediaPlatform(String domain, String appId, String sharedSecret, @Nullable OkHttpClient httpClient) {
        this(new Configuration(domain, appId, sharedSecret), httpClient);
    }

    public MediaPlatform(Configuration configuration, @Nullable OkHttpClient httpClient) {

        ObjectMapper objectMapper = getMapper();
        Authenticator authenticator = new Authenticator(configuration);

        AuthenticatedHTTPClient authenticatedHTTPClient;
        if (null == httpClient) {
            authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator,
                    getHttpClient(), objectMapper);
        } else {
            authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator,
                    httpClient, objectMapper);
        }

        this.archiveService = new ArchiveService(configuration, authenticatedHTTPClient);

        FileUploader fileUploader = new FileUploader(configuration, authenticatedHTTPClient, objectMapper);
        this.fileDownloader = new FileDownloader(configuration, authenticator);
        this.fileService = new FileService(configuration, authenticatedHTTPClient, fileUploader);
        this.jobManager = new JobManager(configuration, authenticatedHTTPClient);
        this.transcodeManager = new TranscodeManager(configuration, authenticatedHTTPClient);
    }

    public FileDownloader fileDownloader() {
        return fileDownloader;
    }

    public FileService fileManager() {
        return fileService;
    }

    public JobManager jobManager() {
        return jobManager;
    }

    public TranscodeManager transcodeManager() {
        return transcodeManager;
    }

    public ArchiveService archiveManager() {
        return archiveService;
    }

    public static ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    public static OkHttpClient getHttpClient() {
        // todo: retry strategy
        return new OkHttpClient();
    }
}
