package com.wix.mediaplatform.image.option.pixelate;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.greaterThan;

public class Pixelate extends Option {

    private static final String KEY = "pix";

    private int pixels;

    public Pixelate(int pixels) {
        super(KEY);
        if (!greaterThan(pixels, 0)) {
            throw new IllegalArgumentException(pixels + " is not in greater than 0");
        }
        this.pixels = pixels;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + pixels;
    }
}
