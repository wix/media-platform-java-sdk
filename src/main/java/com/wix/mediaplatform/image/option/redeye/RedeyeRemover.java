package com.wix.mediaplatform.image.option.redeye;

import com.wix.mediaplatform.image.option.Option;

public class RedeyeRemover extends Option {

    public static final String KEY = "eye";

    public RedeyeRemover() {
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
