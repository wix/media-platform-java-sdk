package com.wix.mediaplatform.v8.service.archive;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.Callback;
import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;
import com.wix.mediaplatform.v8.service.Source;

public class ExtractArchiveRequest extends MediaPlatformRequest<ExtractArchiveJob> {

    private Source source;

    private Destination destination;

    private ExtractedFilesReport extractedFilesReport;

    private Callback jobCallback;

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

    public ExtractArchiveRequest setJobCallback(Callback jobCallback) {
        this.jobCallback = jobCallback;
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

    public Callback getJobCallback() {
        return jobCallback;
    }
}
