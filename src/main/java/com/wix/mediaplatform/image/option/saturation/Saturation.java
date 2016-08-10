package com.wix.mediaplatform.image.option.saturation;

import com.wix.mediaplatform.image.option.Option;

public class Saturation extends Option {

    private static final String KEY = "sat";

    private int saturation;

    public Saturation(int saturation) {
        super(KEY);
        this.saturation = saturation;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + saturation;
    }
}
