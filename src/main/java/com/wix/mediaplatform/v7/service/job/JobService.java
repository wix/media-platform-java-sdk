package com.wix.mediaplatform.v7.service.job;

import com.wix.mediaplatform.v7.configuration.Configuration;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.Job;
import com.wix.mediaplatform.v7.service.MediaPlatformService;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class JobService extends MediaPlatformService {

    private static final long AWAIT_TIMEOUT = 600;
    private static final long AWAIT_INTERVAL = 10;

    public JobService(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        super(configuration, authenticatedHTTPClient);
    }

    public JobRequest jobRequest(String jobId) {
        return new JobRequest(authenticatedHTTPClient, baseUrl, jobId);
    }

    public JobGroupRequest jobGroupRequest(String groupId) {
        return new JobGroupRequest(authenticatedHTTPClient, baseUrl, groupId);
    }

    public JobListRequest jobListRequest() {
        return new JobListRequest(authenticatedHTTPClient, baseUrl);
    }

    // EXPERIMENTAL
    // TODO: move into Job
    @Nullable
    public <R> R awaitJob(final Job<R> job) throws MediaPlatformException {
        await().pollInterval(AWAIT_INTERVAL, TimeUnit.SECONDS)
                .atMost(AWAIT_TIMEOUT, TimeUnit.SECONDS)
                .until(() ->
                        jobRequest(job.getId()).execute().done()
                );

        //noinspection unchecked
        return (R) jobRequest(job.getId()).execute().getResult().throwOrReturn().getPayload();
    }
}
