package com.wix.mediaplatform.v6.management;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.gson.Types;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;
import com.wix.mediaplatform.v6.service.request.SearchJobsRequest;
import com.wix.mediaplatform.v6.service.response.SearchJobsResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JobManager {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String apiBaseUrl;

    public JobManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        this.apiBaseUrl = configuration.getBaseUrl() + "/_api";

        this.authenticatedHTTPClient = authenticatedHTTPClient;
    }

    public Job getJob(String jobId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<Job> restResponse = authenticatedHTTPClient.get(
                apiBaseUrl + "/jobs/" + jobId,
                null,
                Types.JOB_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public Job[] getJobGroup(String groupId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<Job[]> restResponse = authenticatedHTTPClient.get(
                apiBaseUrl + "/jobs/groups/" + groupId,
                null,
                Types.JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public SearchJobsResponse searchJobs(SearchJobsRequest searchJobsRequest) throws MediaPlatformException, IOException, URISyntaxException {
        Map<String, String> params = newHashMap();
        if (searchJobsRequest != null) {
            params.putAll(searchJobsRequest.toParams());
        }

        RestResponse<SearchJobsResponse> restResponse = authenticatedHTTPClient.get(
                apiBaseUrl + "/jobs",
                params,
                Types.SEARCH_JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
