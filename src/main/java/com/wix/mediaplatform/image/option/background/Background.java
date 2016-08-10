package com.wix.mediaplatform.image.option.background;

import com.wix.mediaplatform.image.option.Option;

public class Background extends Option {

    private static final String KEY = "c";

    private String color;

    public Background(String color) {
        super(KEY);
        this.color = color;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + color;
    }
}
