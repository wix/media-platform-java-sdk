package com.wix.mediaplatform.v8.service.transcode;

import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.Specification;

public class TranscodeSpecification implements Specification {

    private Destination destination;

    private QualityRange qualityRange;

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

    public QualityRange getQualityRange() { return qualityRange; }

    public Video getVideo() {
        return video;
    }

    public Audio getAudio() {
        return audio;
    }

    public TranscodeSpecification setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public TranscodeSpecification setQuality(String quality) {
        this.quality = quality;
        return this;
    }

    public TranscodeSpecification setQualityRange(QualityRange qualityRange) {
        this.qualityRange = qualityRange;
        return this;
    }

    public TranscodeSpecification setVideo(Video video) {
        this.video = video;
        return this;
    }

    public TranscodeSpecification setAudio(Audio audio) {
        this.audio = audio;
        return this;
    }

    @Override
    public String toString() {
        return "TranscodeSpecification{" +
                "destination=" + destination +
                ", quality='" + quality + '\'' +
                ", qualityRange='" + qualityRange + '\'' +
                ", video=" + video +
                ", audio=" + audio +
                '}';
    }
}
