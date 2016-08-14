package com.wix.mediaplatform.image.option.pixelate;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.greaterThan;

public class PixelateFaces extends Option {

    private static final String KEY = "pixfs";

    private int pixels;

    public PixelateFaces(int pixels) {
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
