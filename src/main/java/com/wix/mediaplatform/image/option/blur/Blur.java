package com.wix.mediaplatform.image.option.blur;

import com.wix.mediaplatform.image.option.Option;

public class Blur extends Option {

    private static final String KEY = "blur";

    private int percentage;

    public Blur(int percentage) {
        super(KEY);
        this.percentage = percentage;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + percentage;
    }
}
