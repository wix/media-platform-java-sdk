package com.wix.mediaplatform.dto.job;

public class Destination {

    private String path;

    private String directory;

    private String acl = "private";

    public Destination() {
    }

    public Destination setPath(String path) {
        this.path = path;
        this.directory = null;
        return this;
    }

    public Destination setDirectory(String directory) {
        this.directory = directory;
        this.path = null;
        return this;
    }

    public Destination setAcl(String acl) {
        this.acl = acl;
        return this;
    }

    public String getPath() {
        return path;
    }

    public String getDirectory() {
        return directory;
    }

    public String getAcl() {
        return acl;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "path='" + path + '\'' +
                ", directory='" + directory + '\'' +
                ", acl='" + acl + '\'' +
                '}';
    }
}
