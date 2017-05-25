package com.wix.mediaplatform.dto.job;

public class TranscodeSpecification implements Specification {

    private Destination destination;

    private String quality;

    private Video video;

    private Audio audio;

    public TranscodeSpecification() {
    }

    public Destination getDestination() {
        return destination;
    }

    public String getQuality() {
        return quality;
    }

    public Video getVideo() {
        return video;
    }

    public Audio getAudio() {
        return audio;
    }

    @Override
    public String toString() {
        return "TranscodeSpecification{" +
                "destination=" + destination +
                ", quality='" + quality + '\'' +
                ", video=" + video +
                ", audio=" + audio +
                '}';
    }
}
