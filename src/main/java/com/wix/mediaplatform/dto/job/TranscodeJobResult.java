package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

public class TranscodeJobResult {

    private VideoInfo info;

    private FileDescriptor file;

    public TranscodeJobResult() {
    }

    public VideoInfo getInfo() {
        return info;
    }

    public FileDescriptor getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "TranscodeJobResult{" +
                "info=" + info +
                ", file=" + file +
                '}';
    }
}
