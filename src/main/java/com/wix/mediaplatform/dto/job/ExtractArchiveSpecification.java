package com.wix.mediaplatform.dto.job;


import com.wix.mediaplatform.dto.request.ExtractedFilesReport;

public class ExtractArchiveSpecification implements Specification {

    private Source source;

    private Destination destination;

    private ExtractedFilesReport extractedFilesReport = null;

    public ExtractArchiveSpecification() {
    }

    public Source getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractedFilesReport getExtractedFilesReport() { return extractedFilesReport; }

    @Override
    public String toString() {
        return "ExtractArchiveSpecification{" +
                "source=" + source +
                ", destination=" + destination +
                ", extractedFilesReport=" + extractedFilesReport +
                '}';
    }

}
