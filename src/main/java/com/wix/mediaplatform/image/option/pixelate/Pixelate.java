package com.wix.mediaplatform.image.option.pixelate;

import com.wix.mediaplatform.image.option.Option;

public class Pixelate extends Option {

    private static final String KEY = "pix";

    private int pixels;

    public Pixelate(int pixels) {
        super(KEY);
        this.pixels = pixels;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + pixels;
    }
}
