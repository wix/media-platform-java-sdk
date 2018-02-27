package com.wix.mediaplatform.dto.job;

public class ArchiveSource extends Source {

    private String pathInArchive;

    public ArchiveSource() {
        super();
    }

    @Override
    public ArchiveSource setFileId(String fileId) {
        super.setFileId(fileId);
        return this;
    }

    @Override
    public ArchiveSource setPath(String path) {
        super.setPath(path);
        return this;
    }

    public ArchiveSource setPathInArchive(String pathInArchive) {
        this.pathInArchive = pathInArchive;
        return this;
    }

    public String getPathInArchive() {
        return pathInArchive;
    }
}
