package com.wix.mediaplatform.image.option.pixelate;

import com.wix.mediaplatform.image.option.Option;

public class PixelateFaces extends Option {

    private static final String KEY = "pixfs";

    private int pixels;

    public PixelateFaces(int pixels) {
        super(KEY);
        this.pixels = pixels;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + pixels;
    }
}
