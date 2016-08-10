package com.wix.mediaplatform.image.option.brightness;

import com.wix.mediaplatform.image.option.Option;

public class Brightness extends Option {

    private static final String KEY = "br";

    private int brightness;

    public Brightness(int brightness) {
        super(KEY);
        this.brightness = brightness;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + brightness;
    }
}
