package com.wix.mediaplatform.image.option.negative;

import com.wix.mediaplatform.image.option.Option;

public class Negative extends Option {

    public static final String KEY = "neg";

    public Negative() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }

    @Override
    public Option deserialize(String... params) {
        return this;
    }
}
