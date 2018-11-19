package com.wix.mediaplatform.v6.service.job;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformService;

public class JobService extends MediaPlatformService {

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
}
