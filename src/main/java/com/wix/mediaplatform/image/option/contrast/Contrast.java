package com.wix.mediaplatform.image.option.contrast;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;

public class Contrast extends Option {

    private static final String KEY = "con";

    private int contrast;

    public Contrast(int contrast) {
        super(KEY);
        if (!inRange(contrast, -100, 100)) {
            throw new IllegalArgumentException(contrast + " is not in range [-100,100]");
        }
        this.contrast = contrast;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + contrast;
    }
}
