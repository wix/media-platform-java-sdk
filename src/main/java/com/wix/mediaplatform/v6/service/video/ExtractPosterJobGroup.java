package com.wix.mediaplatform.v6.service.video;


public class ExtractPosterJobGroup {

    private ExtractPosterJob[] jobs;

    private String groupId;

    public ExtractPosterJobGroup() {
    }

    public ExtractPosterJob[] getJobs() {
        return jobs;
    }

    public ExtractPosterJobGroup setJobs(ExtractPosterJob[] jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public ExtractPosterJobGroup setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
