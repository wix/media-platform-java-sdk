package com.wix.mediaplatform.v7.service.job;

import com.wix.mediaplatform.v7.service.Job;

public class JobGroup {

    private Job[] jobs;

    private String groupId;

    public JobGroup() {
    }

    public Job[] getJobs() {
        return jobs;
    }

    public JobGroup setJobs(Job[] jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public JobGroup setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
