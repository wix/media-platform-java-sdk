package com.wix.mediaplatform.image.option.sharpen;

import com.wix.mediaplatform.image.option.Option;

public class Sharpen extends Option {

    private static final String KEY = "shrp";

    private int radius;

    public Sharpen(int radius) {
        super(KEY);
        this.radius = radius;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + radius;
    }
}
