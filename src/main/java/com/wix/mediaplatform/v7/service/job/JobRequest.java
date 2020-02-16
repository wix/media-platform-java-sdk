package com.wix.mediaplatform.v7.service.job;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.Job;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class JobRequest extends MediaPlatformRequest<Job> {

    JobRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String jobId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/jobs/" + jobId, Job.class);
    }

}
