package com.wix.mediaplatform.image.option;

public abstract class Option {

    private String key;

    public Option(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public abstract String serialize();
}
