package com.wix.mediaplatform.dto.job;


public class FileImportSpecification implements Specification {

    private String sourceUrl;

    private Destination destination;

    public FileImportSpecification() {
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "FileImportSpecification{" +
                "sourceUrl='" + sourceUrl + '\'' +
                ", destination=" + destination +
                '}';
    }
}
