package com.wix.mediaplatform.image.option.sharpen;

import com.wix.mediaplatform.image.option.Option;

public class Sharpen extends Option {

    private static final String KEY = "shrp";

    private float radius;

    public Sharpen(float radius) {
        super(KEY);
        this.radius = radius;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + DECIMAL_FORMAT.format(radius);
    }
}
