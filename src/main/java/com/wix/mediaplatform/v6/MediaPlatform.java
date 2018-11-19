package com.wix.mediaplatform.v6;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.archive.ArchiveService;
import com.wix.mediaplatform.v6.service.file.FileService;
import com.wix.mediaplatform.v6.service.job.JobService;
import com.wix.mediaplatform.v6.service.live.LiveService;
import com.wix.mediaplatform.v6.service.transcode.TranscodeService;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;

public class MediaPlatform {

    public static int MAX_RETRIES = 5;

    private final FileService fileService;
    private final JobService jobService;
    private final ArchiveService archiveService;
    private final TranscodeService transcodeService;
    private final LiveService liveService;

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
            authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, getHttpClient(), objectMapper);
        } else {
            authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, objectMapper);
        }

        this.fileService = new FileService(configuration, authenticatedHTTPClient, objectMapper, authenticator);
        this.archiveService = new ArchiveService(configuration, authenticatedHTTPClient);
        this.jobService = new JobService(configuration, authenticatedHTTPClient);
        this.transcodeService = new TranscodeService(configuration, authenticatedHTTPClient);
        this.liveService = new LiveService(configuration, authenticatedHTTPClient);
    }

    public FileService fileManager() {
        return fileService;
    }

    public JobService jobManager() {
        return jobService;
    }

    public TranscodeService transcodeManager() {
        return transcodeService;
    }

    public ArchiveService archiveManager() {
        return archiveService;
    }

    public LiveService liveService() {
        return liveService;
    }

    public static ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static OkHttpClient getHttpClient() {
        // todo: retry strategy
        return new OkHttpClient();
    }
}
