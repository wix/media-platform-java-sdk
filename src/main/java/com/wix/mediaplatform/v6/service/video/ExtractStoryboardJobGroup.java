package com.wix.mediaplatform.v6.service.video;


public class ExtractStoryboardJobGroup {

    private ExtractStoryboardJob[] jobs;

    private String groupId;

    public ExtractStoryboardJobGroup() {
    }

    public ExtractStoryboardJob[] getJobs() {
        return jobs;
    }

    public ExtractStoryboardJobGroup setJobs(ExtractStoryboardJob[] jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public ExtractStoryboardJobGroup setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
