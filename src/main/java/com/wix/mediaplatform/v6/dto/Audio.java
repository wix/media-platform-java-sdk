package com.wix.mediaplatform.v6.dto;

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
