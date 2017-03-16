package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.FileDescriptor;

import java.util.Set;

public class ListFilesResponse {

    @SerializedName("ts")
    private long timeStamp;

    private int count;

    @SerializedName("cursor")
    private String nextPageCursor;

    private Set<FileDescriptor> files;

    public ListFilesResponse() {
    }

    public ListFilesResponse(long timeStamp, int count, String nextPageCursor, Set<FileDescriptor> files) {
        this.timeStamp = timeStamp;
        this.count = count;
        this.nextPageCursor = nextPageCursor;
        this.files = files;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getCount() {
        return count;
    }

    public String getNextPageCursor() {
        return nextPageCursor;
    }

    public Set<FileDescriptor> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "ListFilesResponse{" +
                "timeStamp=" + timeStamp +
                ", count=" + count +
                ", nextPageCursor='" + nextPageCursor + '\'' +
                ", files=" + files +
                '}';
    }
}
