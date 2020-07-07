package com.wix.mediaplatform.v8.service.job;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.Job;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class JobGroupRequest extends MediaPlatformRequest<Job[]> {

    JobGroupRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String groupId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/jobs/groups/" + groupId, Job[].class);
    }

}
