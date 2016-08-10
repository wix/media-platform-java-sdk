package com.wix.mediaplatform.image.option.hue;

import com.wix.mediaplatform.image.option.Option;

public class Hue extends Option {

    private static final String KEY = "hue";

    private int hue;

    public Hue(int hue) {
        super(KEY);
        this.hue = hue;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + hue;
    }
}
