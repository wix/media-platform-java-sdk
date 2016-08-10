package com.wix.mediaplatform.image.option.alignment;

import com.wix.mediaplatform.image.option.Option;

public class Align extends Option {

    private static final String KEY = "al";

    private Alignment alignment;

    public Align(Alignment alignment) {
        super(KEY);
        this.alignment = alignment;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + alignment.getValue();
    }
}
