package com.wix.mediaplatform.image.option.encoding.jpeg;

import com.wix.mediaplatform.image.option.Option;

public class Baseline extends Option {

    private static final String KEY = "bl";

    public Baseline() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }
}
