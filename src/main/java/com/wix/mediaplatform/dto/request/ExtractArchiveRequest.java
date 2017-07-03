package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.Source;

public class ExtractArchiveRequest {

    private Source source;

    private Destination destination;

    private ExtractedFilesReport extractedFilesReport = null;

    public ExtractArchiveRequest() {
    }

    public ExtractArchiveRequest setSource(Source source) {
        this.source = source;
        return this;
    }

    public ExtractArchiveRequest setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public ExtractArchiveRequest setExtractedFilesReport(ExtractedFilesReport extractedFilesReport) {
        this.extractedFilesReport = extractedFilesReport;
        return this;
    }
}
