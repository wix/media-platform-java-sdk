package com.wix.mediaplatform.image.option.negative;

import com.wix.mediaplatform.image.option.Option;

public class Negative extends Option {

    private static final String KEY = "neg";

    public Negative() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }
}
