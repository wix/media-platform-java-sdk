package com.wix.mediaplatform.image.option.contrast;

import com.wix.mediaplatform.image.option.Option;

public class Contrast extends Option {

    private static final String KEY = "con";

    private int contrast;

    public Contrast(int contrast) {
        super(KEY);
        this.contrast = contrast;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + contrast;
    }
}
