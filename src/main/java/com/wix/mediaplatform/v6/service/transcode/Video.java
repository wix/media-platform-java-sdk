package com.wix.mediaplatform.v6.service.transcode;

public class Video {

    private VideoSpecification specification;

    public Video() {
    }

    public VideoSpecification getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        return "Video{" +
                "specification=" + specification +
                '}';
    }
}
