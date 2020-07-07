package com.wix.mediaplatform.v8.service.transcode;

public class Audio {

    private AudioSpecification specification;

    public Audio() {
    }

    public AudioSpecification getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "specification=" + specification +
                '}';
    }
}
