package com.wix.mediaplatform.dto.job;


public class ExtractArchiveSpecification {

    public Source source;

    public Destination destination;

    public ExtractArchiveSpecification() {
    }

    public Source getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "ExtractArchiveSpecification{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
}
