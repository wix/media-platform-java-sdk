package com.wix.mediaplatform.v8.service.video;

import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.Specification;

public class ExtractPosterSpecification implements Specification {

    private float second;

    private Destination destination;

    private String format = "jpg";

    public ExtractPosterSpecification() {
    }

    public float getSecond() {
        return second;
    }

    public ExtractPosterSpecification setSecond(float second) {
        this.second = second;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractPosterSpecification setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public ExtractPosterSpecification setFormat(String format) {
        this.format = format;
        return this;
    }
}
