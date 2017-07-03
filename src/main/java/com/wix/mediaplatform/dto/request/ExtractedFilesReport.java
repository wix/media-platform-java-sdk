package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.job.Destination;

/**
 * Created by alonne on 03/07/2017.
 */
public class ExtractedFilesReport {
    public static class Format {
        public static final String json = "json";
        public static final String csv = "csv";
    }

    private Destination destination;

    private String format;


    public ExtractedFilesReport() {
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractedFilesReport setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public ExtractedFilesReport setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public String toString() {
        return "ExtractedFilesReport{" +
                "destination=" + destination +
                ", format='" + format + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtractedFilesReport that = (ExtractedFilesReport) o;

        if (!destination.equals(that.destination)) return false;
        return format.equals(that.format);
    }

    @Override
    public int hashCode() {
        int result = destination.hashCode();
        result = 31 * result + format.hashCode();
        return result;
    }
}
