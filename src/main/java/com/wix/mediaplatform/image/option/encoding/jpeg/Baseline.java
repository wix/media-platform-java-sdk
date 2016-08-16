package com.wix.mediaplatform.image.option.encoding.jpeg;

import com.wix.mediaplatform.image.option.Option;

public class Baseline extends Option {

    public static final String KEY = "bl";

    public Baseline() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }

    @Override
    public Option deserialize(String... params) {
        return this;
    }

    @Override
    public String toString() {
        return "Baseline{}";
    }
}
