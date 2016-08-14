package com.wix.mediaplatform.image.option.oil;

import com.wix.mediaplatform.image.option.Option;

public class Oil extends Option {

    private static final String KEY = "oil";

    public Oil() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }
}
