package com.wix.mediaplatform.dto.metadata.basic;


public class VideoBasicMetadata implements BasicMetadata {

    private Boolean interlaced;

    private VideoStream[] videoStreams;

    private AudioStream[] audioStreams;

    private VideoFormat format;

    public VideoBasicMetadata() {
    }

    public Boolean getInterlaced() {
        return interlaced;
    }

    public VideoStream[] getVideoStreams() {
        return videoStreams;
    }

    public AudioStream[] getAudioStreams() {
        return audioStreams;
    }

    public VideoFormat getFormat() {
        return format;
    }
}
