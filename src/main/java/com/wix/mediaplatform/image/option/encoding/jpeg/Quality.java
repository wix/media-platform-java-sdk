package com.wix.mediaplatform.image.option.encoding.jpeg;

import com.wix.mediaplatform.image.option.Option;

public class Quality extends Option {

    private static final String KEY = "q";

    private int quality;

    public Quality(int quality) {
        super(KEY);
        this.quality = quality;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + quality;
    }
}
