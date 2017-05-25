package com.wix.mediaplatform.dto.job;

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
