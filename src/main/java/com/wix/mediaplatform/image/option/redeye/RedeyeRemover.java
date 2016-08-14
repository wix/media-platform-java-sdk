package com.wix.mediaplatform.image.option.redeye;

import com.wix.mediaplatform.image.option.Option;

public class RedeyeRemover extends Option {

    private static final String KEY = "eye";

    public RedeyeRemover() {
        super(KEY);
    }

    @Override
    public String serialize() {
        return KEY;
    }
}
