package com.wix.mediaplatform.v6.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FileDescriptor {

    private String id;

    private String hash;

    private String path;

    private String mimeType;

    private Type type;

    private long size;

    private Acl acl;

    private Date dateCreated;

    private Date dateUpdated;

    private FileLifecycle lifecycle;

    public FileDescriptor setId(String id) {
        this.id = id;
        return this;
    }

    public FileDescriptor setPath(String path) {
        this.path = path;
        return this;
    }

    public FileDescriptor setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public FileDescriptor setType(Type type) {
        this.type = type;
        return this;
    }

    public FileDescriptor setSize(long size) {
        this.size = size;
        return this;
    }

    public FileDescriptor setAcl(Acl acl) {
        this.acl = acl;
        return this;
    }

    public FileDescriptor setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public FileDescriptor setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public FileDescriptor setLifecycle(FileLifecycle lifecycle) {
        this.lifecycle = lifecycle;
        return this;
    }

    public FileDescriptor() {
    }

    public String getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getPath() {
        return path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getType() {
        return type.getValue();
    }

    public long getSize() {
        return size;
    }

    public String getAcl() {
        return acl.getValue();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public FileLifecycle getLifecycle() {
        return lifecycle;
    }

    public enum Acl {

        @JsonProperty("public")
        PUBLIC("public"),
        @JsonProperty("private")
        PRIVATE("private");

        private final String value;

        Acl(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Acl fromString(String aclString) {
            for (Acl acl: Acl.values()) {
                if (acl.value.equals(aclString)) {
                    return acl;
                }
            }

            throw new IllegalArgumentException("Invalid value for acl: " + aclString);
        }
    }

    public enum Type {

        @JsonProperty("-")
        FILE("-"),
        @JsonProperty("d")
        DIRECTORY("d");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type fromString(String typeString) {
            for (Type type: Type.values()) {
                if (type.value.equals(typeString)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid value for file type: " + typeString);
        }
    }
}


