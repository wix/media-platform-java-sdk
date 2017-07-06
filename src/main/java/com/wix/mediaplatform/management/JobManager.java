package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.request.SearchJobsRequest;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.dto.response.SearchJobsResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.gson.Types.*;

public class JobManager {

    private final Configuration configuration;

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String apiBaseUrl;

    public JobManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        this.configuration = configuration;

        this.apiBaseUrl = "https://" + configuration.getDomain() + "/_api";

        this.authenticatedHTTPClient = authenticatedHTTPClient;
    }

    public Job getJob(String jobId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<Job> restResponse = authenticatedHTTPClient.get(
                apiBaseUrl + "/jobs/" + jobId,
                null,
                JOB_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public Job[] getJobGroup(String groupId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<Job[]> restResponse = authenticatedHTTPClient.get(
                apiBaseUrl + "/jobs/groups/" + groupId,
                null,
                JOBS_REST_RESPONSE);

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
                SEARCH_JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
