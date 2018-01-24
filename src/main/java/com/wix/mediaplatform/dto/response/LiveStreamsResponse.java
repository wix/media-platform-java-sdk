package com.wix.mediaplatform.dto.response;

import com.wix.mediaplatform.dto.live.LiveStream;

import java.util.Arrays;

public class LiveStreamsResponse {

    private LiveStream[] liveStreams;

    public LiveStreamsResponse() {
    }


    public LiveStream[] getStreams() {
        return liveStreams;
    }

    @Override
    public String toString() {
        return "LiveStreamsResponse{" +
                "streams=" + Arrays.toString(liveStreams) +
                '}';
    }}
