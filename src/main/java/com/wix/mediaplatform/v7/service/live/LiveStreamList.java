package com.wix.mediaplatform.v7.service.live;

public class LiveStreamList {

    private String nextPageToken;

    private LiveStream[] liveStreams;

    public LiveStreamList() {
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public LiveStream[] getLiveStreams() {
        return liveStreams;
    }
}
