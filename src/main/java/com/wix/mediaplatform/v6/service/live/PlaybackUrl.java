package com.wix.mediaplatform.v6.service.live;

public class PlaybackUrl {
    private String packageName;
    private String path;

    public PlaybackUrl() {
    }

    public String getPackageName() {
        return packageName;
    }

    public PlaybackUrl setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public PlaybackUrl setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "PlaybackUrl{" +
                "packageName=" + packageName +
                ",path=" + path +
                '}';
    }
}
