package com.wix.mediaplatform.dto.metadata;

import java.util.Date;

public class FileDescriptor {

    private String id;

    private String hash;

    private String path;

    private String mimeType;

    private String type;

    private long size;

    private String acl;

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
        return type;
    }

    public long getSize() {
        return size;
    }

    public String getAcl() {
        return acl;
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
                ", job_type='" + type + '\'' +
                ", size=" + size +
                ", acl='" + acl + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}


