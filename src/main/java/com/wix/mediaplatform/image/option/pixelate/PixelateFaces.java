package com.wix.mediaplatform.image.option.pixelate;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.greaterThan;
import static java.lang.Integer.parseInt;

public class PixelateFaces extends Option {

    public static final String KEY = "pixfs";

    private int pixels;

    public PixelateFaces() {
        super(KEY);
    }

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

    @Override
    public Option deserialize(String... params) {
        pixels = parseInt(params[0]);
        return this;
    }
}
