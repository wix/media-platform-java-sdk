package com.wix.mediaplatform.v6.service.job;

import com.wix.mediaplatform.v6.service.Job;

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
