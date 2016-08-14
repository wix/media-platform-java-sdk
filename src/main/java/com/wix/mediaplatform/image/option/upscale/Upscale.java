package com.wix.mediaplatform.image.option.upscale;

import com.wix.mediaplatform.image.option.Option;

public class Upscale extends Option {

    private static final String KEY = "lg";

    public Upscale() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }
}
