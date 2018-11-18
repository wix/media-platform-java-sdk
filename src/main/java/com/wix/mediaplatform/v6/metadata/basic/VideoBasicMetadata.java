package com.wix.mediaplatform.v6.metadata.basic;


import java.util.Arrays;

public class VideoBasicMetadata extends BasicMetadata {

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

    @Override
    public String toString() {
        return "VideoBasicMetadata{" +
                "interlaced=" + interlaced +
                ", videoStreams=" + Arrays.toString(videoStreams) +
                ", audioStreams=" + Arrays.toString(audioStreams) +
                ", format=" + format +
                '}';
    }
}
