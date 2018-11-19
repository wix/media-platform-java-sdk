package com.wix.mediaplatform.v6.service.archive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wix.mediaplatform.v6.service.Destination;


public class ExtractedFilesReport {

    private Destination destination;

    private ExtractedFilesReport.Format format;

    public ExtractedFilesReport() {
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractedFilesReport setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public ExtractedFilesReport.Format getFormat() {
        return format;
    }

    public ExtractedFilesReport setFormat(ExtractedFilesReport.Format format) {
        this.format = format;
        return this;
    }

    public enum Format {
        @JsonProperty("json")
        json("json"),
        @JsonProperty("csv")
        csv("csv");

        private final String value;

        Format(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
