package com.wix.mediaplatform.v6.service;

public class Destination {

    private String path;

    private String directory;

    private FileDescriptor.Acl acl = FileDescriptor.Acl.PRIVATE;

    private FileLifecycle fileLifecycle;

    public Destination() {
    }

    public String getPath() {
        return path;
    }

    public Destination setPath(String path) {
        this.path = path;
        return this;
    }

    public String getDirectory() {
        return directory;
    }

    public Destination setDirectory(String directory) {
        this.directory = directory;
        return this;
    }

    public FileDescriptor.Acl getAcl() {
        return acl;
    }

    public Destination setAcl(FileDescriptor.Acl acl) {
        this.acl = acl;
        return this;
    }

    public FileLifecycle getFileLifecycle() {
        return fileLifecycle;
    }

    public Destination setFileLifecycle(FileLifecycle fileLifecycle) {
        this.fileLifecycle = fileLifecycle;
        return this;
    }
}
