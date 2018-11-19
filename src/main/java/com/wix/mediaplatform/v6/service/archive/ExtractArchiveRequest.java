package com.wix.mediaplatform.v6.service.archive;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;
import com.wix.mediaplatform.v6.service.Source;

public class ExtractArchiveRequest extends MediaPlatformRequest<ExtractArchiveJob> {

    private Source source;

    private Destination destination;

    private ExtractedFilesReport extractedFilesReport;

    ExtractArchiveRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/archive/extract", ExtractArchiveJob.class);
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

    public Source getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractedFilesReport getExtractedFilesReport() {
        return extractedFilesReport;
    }
}
