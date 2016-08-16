package com.wix.mediaplatform.image.option;

import java.text.DecimalFormat;

public abstract class Option {

    protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    protected static final String SEPARATOR = "_";

    private String key;

    public Option(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public abstract String serialize();

    public abstract Option deserialize(String... params);
}
