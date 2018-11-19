package com.wix.mediaplatform.v6.service;

import com.fasterxml.jackson.annotation.JsonProperty;

// todo: register sub types
public abstract class Job {

    private String id;

    private Type type;

    private String issuer;

    private Status status;

    private String groupId;

    private Source[] sources;

    private String dateCreated;

    private String dateUpdated;

    public String getId() {
        return id;
    }

    public String getType() {
        return type.getValue();
    }

    public String getIssuer() {
        return issuer;
    }

    public String getStatus() {
        return status.name();
    }

    public String getGroupId() {
        return groupId;
    }

    public Source[] getSources() {
        return sources;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public abstract Specification getSpecification();

    public abstract RestResponse getResult();

    public Job setId(String id) {
        this.id = id;
        return this;
    }

    public Job setType(Type type) {
        this.type = type;
        return this;
    }

    public Job setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public Job setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Job setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Job setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public Job setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Job setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public enum Type {
        @JsonProperty("urn:job:av.transcode")
        TRANSCODE("urn:job:av.transcode"),
        @JsonProperty("urn:job:archive.create")
        ARCHIVE_CREATE("urn:job:archive.create"),
        @JsonProperty("urn:job:archive.extract")
        ARCHIVE_EXTRACT("urn:job:archive.extract"),
        @JsonProperty("urn:job:import.file")
        FILE_IMPORT("urn:job:import.file"),

        // obsolete, here for backwards compatibility only
        @JsonProperty("urn:job:av.package")
        PACKAGE("urn:job:av.package"),
        @JsonProperty("urn:job:replication.enable")
        REPLICATION_ENABLE("urn:job:replication.enable"),
        @JsonProperty("urn:job:replication.disable")
        REPLICATION_DISABLE("urn:job:replication.disable");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type fromString(String typeString) {
            for (Type type: Type.values()) {
                if (type.getValue().equals(typeString)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid value for job type: " + typeString);
        }
    }

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
}
