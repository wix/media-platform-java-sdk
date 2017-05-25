package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

import java.util.Arrays;

public abstract class Job {

    private String id;

    private String type;

    private String issuer;

    private String status;

    private String groupId;

    private Source[] sources;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getStatus() {
        return status;
    }

    public String getGroupId() {
        return groupId;
    }

    public Source[] getSources() {
        return sources;
    }

    public abstract Specification getSpecification();

    public abstract RestResponse getResult();

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", issuer='" + issuer + '\'' +
                ", status='" + status + '\'' +
                ", groupId='" + groupId + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", specification=" + getSpecification() +
                ", result=" + getResult() +
                '}';
    }
}
