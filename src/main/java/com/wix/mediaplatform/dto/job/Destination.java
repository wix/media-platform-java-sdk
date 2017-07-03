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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (directory != null ? !directory.equals(that.directory) : that.directory != null) return false;
        return acl != null ? acl.equals(that.acl) : that.acl == null;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (directory != null ? directory.hashCode() : 0);
        result = 31 * result + (acl != null ? acl.hashCode() : 0);
        return result;
    }
}
