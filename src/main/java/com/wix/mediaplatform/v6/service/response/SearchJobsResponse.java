package com.wix.mediaplatform.v6.service.response;

import com.wix.mediaplatform.v6.service.Job;

import java.util.Arrays;

public class SearchJobsResponse {

    private String nextPageToken;

    private Job[] jobs;

    public SearchJobsResponse() {
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public Job[] getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "SearchJobsResponse{" +
                "nextPageToken='" + nextPageToken + '\'' +
                ", jobs=" + Arrays.toString(jobs) +
                '}';
    }
}
