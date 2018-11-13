package com.wix.mediaplatform.v6.service.response;

import com.wix.mediaplatform.v6.service.Job;

import java.util.Arrays;

public class TranscodeJobsResponse {

    private Job[] jobs;

    private String groupId;

    public TranscodeJobsResponse() {
    }

    public Job[] getJobs() {
        return jobs;
    }

    public String getGroupId() { return groupId; }

    @Override
    public String toString() {
        return "TranscodeJobsResponse{" +
                "groupId='" + groupId + '\'' +
                ", jobs=" + Arrays.toString(jobs) +
                '}';
    }
}
