package com.wix.mediaplatform.v6.service.transcode;

import com.wix.mediaplatform.v6.service.FileDescriptor;

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
