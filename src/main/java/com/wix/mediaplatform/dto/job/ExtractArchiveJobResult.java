package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

/**
 * Created by alonne on 30/07/2017.
 */
public class ExtractArchiveJobResult {
    private FileDescriptor reportFileDescriptor;

    public ExtractArchiveJobResult() {
    }

    public FileDescriptor getReportFileDescriptor() {
        return reportFileDescriptor;
    }

    @Override
    public String toString() {
        return "ExtractArchiveJobResult{" +
                "reportFileDescriptor=" + reportFileDescriptor +
                '}';
    }
}
