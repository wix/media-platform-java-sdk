package com.wix.mediaplatform.v7;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v7.auth.Authenticator;
import com.wix.mediaplatform.v7.auth.Token;
import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.archive.ArchiveService;
import com.wix.mediaplatform.v7.service.file.FileService;
import com.wix.mediaplatform.v7.service.flowcontrol.FlowControlService;
import com.wix.mediaplatform.v7.service.image.ImageService;
import com.wix.mediaplatform.v7.service.job.JobService;
import com.wix.mediaplatform.v7.service.live.LiveService;
import com.wix.mediaplatform.v7.service.transcode.TranscodeService;
import com.wix.mediaplatform.v7.service.video.VideoService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MediaPlatform {

    public static int MAX_RETRIES = 5;
    public static int INITIAL_DELAY = 250;
    public static int MAX_DELAY = 3000;
    public static int CONNECTION_TIMEOUT = 10;
    public static int TIMEOUT = 0;
    public static Set<Integer> RETRYABLES = new HashSet<>(Arrays.asList(500, 503, 504, 429));

    private final Authenticator authenticator;
    private final FileService fileService;
    private final JobService jobService;
    private final ArchiveService archiveService;
    private final TranscodeService transcodeService;
    private final LiveService liveService;
    private final ImageService imageService;
    private final VideoService videoService;
    private final FlowControlService flowControlService;


    public MediaPlatform(String domain, String appId, String sharedSecret) {
        this(domain, appId, sharedSecret, null);
    }

    public MediaPlatform(String domain, String appId, String sharedSecret, @Nullable OkHttpClient httpClient) {
        this(new Configuration(domain, appId, sharedSecret), httpClient);
    }

    public MediaPlatform(Configuration configuration, @Nullable OkHttpClient httpClient) {

        ObjectMapper objectMapper = getMapper();
        this.authenticator = new Authenticator(configuration);

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
        this.imageService = new ImageService(configuration, authenticatedHTTPClient, authenticator);
        this.videoService = new VideoService(configuration, authenticatedHTTPClient);
        this.flowControlService = new FlowControlService(configuration, authenticatedHTTPClient);
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

    public ImageService imageService() {
        return imageService;
    }

    public VideoService videoService() {
        return videoService;
    }

    public FlowControlService flowControlService() {
        return flowControlService;
    }

    public Token getToken() {
        return authenticator.getToken();
    }

    public String getAuthorizationHeader(Token token) {
        return authenticator.getHeader(token);
    }

    public static ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        return objectMapper;
    }

    public static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .callTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    int attempt = 1;

                    Request request = chain.request();

                    // try the request
                    Response response = null;
                    while (attempt <= MAX_RETRIES) {
                        try {
                            response = chain.proceed(request);

                            if (RETRYABLES.contains(response.code())) {
                                throw new IOException("status: " + response.code() + ", attempt: " + attempt,
                                        new MediaPlatformException("server error", response.code()));
                            }

                            break;
                        } catch (IOException io) {

                            if (null != response) {
                                response.close();
                            }

                            if (attempt >= MAX_RETRIES) {
                                throw io;
                            }

                            try {
                                Thread.sleep(Math.min(INITIAL_DELAY * attempt, MAX_DELAY));
                            } catch (InterruptedException in) {
                                throw new RuntimeException(in);
                            }
                        } finally {
                            attempt++;
                        }
                    }

                    if (null == response) {
                        throw new RuntimeException("no response");
                    }

                    return response;
                })
                .build();
    }
}
