package com.wix.mediaplatform.v6.metadata.features;

public class ExplicitContent {

    private String name;

    private String likelihood;

    public ExplicitContent() {
    }

    public String getName() {
        return name;
    }

    public ExplicitContent setName(String name) {
        this.name = name;
        return this;
    }

    public String getLikelihood() {
        return likelihood;
    }

    public ExplicitContent setLikelihood(String likelihood) {
        this.likelihood = likelihood;
        return this;
    }
}
