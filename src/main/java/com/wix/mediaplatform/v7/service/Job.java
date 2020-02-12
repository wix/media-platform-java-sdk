package com.wix.mediaplatform.v7.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wix.mediaplatform.v7.service.archive.CreateArchiveJob;
import com.wix.mediaplatform.v7.service.archive.ExtractArchiveJob;
import com.wix.mediaplatform.v7.service.file.ImportFileJob;
import com.wix.mediaplatform.v7.service.transcode.TranscodeJob;
import com.wix.mediaplatform.v7.service.video.ExtractPosterJob;
import com.wix.mediaplatform.v7.service.video.ExtractStoryboardJob;

import java.util.Arrays;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateArchiveJob.class, name = "urn:job:archive.create"),
        @JsonSubTypes.Type(value = ExtractArchiveJob.class, name = "urn:job:archive.extract"),
        @JsonSubTypes.Type(value = ImportFileJob.class, name = "urn:job:import.file"),
        @JsonSubTypes.Type(value = TranscodeJob.class, name = "urn:job:av.transcode"),
        @JsonSubTypes.Type(value = ExtractPosterJob.class, name = "urn:job:av.poster"),
        @JsonSubTypes.Type(value = ExtractStoryboardJob.class, name = "urn:job:av.storyboard"),
})
public abstract class Job<R> {

    private String id;

    private String type;

    private String issuer;

    private Status status;

    private String groupId;

    private Source[] sources;

    private String dateCreated;

    private String dateUpdated;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public Job<R> setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Job<R> setType(String type) {
        this.type = type;
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public Job<R> setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Job<R> setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public Job<R> setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Source[] getSources() {
        return sources;
    }

    public Job<R> setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public Job<R> setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public Job<R> setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public boolean done() {
        return status == Status.success || status == Status.error;
    }

    //    todo: support job callback

    public abstract Specification getSpecification();

    public abstract RestResponse<R> getResult();

    public enum Status {

        @JsonProperty("pending")
        pending("pending"),
        @JsonProperty("working")
        working("working"),
        @JsonProperty("success")
        success("success"),
        @JsonProperty("error")
        error("error");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromString(String typeString) {
            return Status.valueOf(typeString);
        }
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", issuer='" + issuer + '\'' +
                ", status=" + status +
                ", groupId='" + groupId + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                '}';
    }
}
