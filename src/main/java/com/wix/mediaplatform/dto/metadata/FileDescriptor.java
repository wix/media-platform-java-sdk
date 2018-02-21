package com.wix.mediaplatform.dto.metadata;

import java.util.Date;

public class FileDescriptor {
    public enum Acl {
        PUBLIC("public"),
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
        FILE("-"),
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

    private String id;

    private String hash;

    private String path;

    private String mimeType;

    private Type type;

    private long size;

    private Acl acl;

    private Date dateCreated;

    private Date dateUpdated;

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

    @Override
    public String toString() {
        return "FileDescriptor{" +
                "id='" + id + '\'' +
                ", hash='" + hash + '\'' +
                ", path='" + path + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", type=" + type +
                ", size=" + size +
                ", acl=" + acl +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}


