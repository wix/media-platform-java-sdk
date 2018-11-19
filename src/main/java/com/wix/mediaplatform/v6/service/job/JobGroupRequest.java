package com.wix.mediaplatform.v6.service.job;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class JobGroupRequest extends MediaPlatformRequest<JobGroup> {

    JobGroupRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String groupId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/jobs/groups/" + groupId, JobGroup.class);
    }

}
