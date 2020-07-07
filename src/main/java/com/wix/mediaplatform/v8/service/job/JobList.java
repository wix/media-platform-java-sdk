package com.wix.mediaplatform.v8.service.job;

import com.wix.mediaplatform.v8.service.Job;

import java.util.Arrays;

public class JobList {

    private String nextPageToken;

    private Job[] jobs;

    public JobList() {
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public Job[] getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "JobList{" +
                "nextPageToken='" + nextPageToken + '\'' +
                ", jobs=" + Arrays.toString(jobs) +
                '}';
    }
}
