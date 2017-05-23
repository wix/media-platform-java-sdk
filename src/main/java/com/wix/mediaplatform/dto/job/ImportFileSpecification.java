package com.wix.mediaplatform.dto.job;


public class ImportFileSpecification implements Specification {

    private String sourceUrl;

    private Destination destination;

    public ImportFileSpecification() {
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "ImportFileSpecification{" +
                "sourceUrl='" + sourceUrl + '\'' +
                ", destination=" + destination +
                '}';
    }
}
