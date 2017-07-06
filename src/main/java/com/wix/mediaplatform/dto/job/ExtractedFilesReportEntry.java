package com.wix.mediaplatform.dto.job;

/**
 * Created by alonne on 04/07/2017.
 */
public class ExtractedFilesReportEntry {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtractedFilesReportEntry that = (ExtractedFilesReportEntry) o;

        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "ExtractedFilesReportEntry{" +
                "path='" + path + '\'' +
                '}';
    }
}
