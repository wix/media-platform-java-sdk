package com.wix.mediaplatform.v7.service.transcode;

public class TranscodeJobGroup {

    private TranscodeJob[] jobs;

    private String groupId;

    public TranscodeJobGroup() {
    }

    public TranscodeJob[] getJobs() {
        return jobs;
    }

    public TranscodeJobGroup setJobs(TranscodeJob[] jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public TranscodeJobGroup setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
