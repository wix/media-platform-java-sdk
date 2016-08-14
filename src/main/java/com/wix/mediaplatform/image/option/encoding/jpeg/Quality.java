package com.wix.mediaplatform.image.option.encoding.jpeg;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;

public class Quality extends Option {

    private static final String KEY = "q";

    private int quality;

    public Quality(int quality) {
        super(KEY);
        if (!inRange(quality, 0, 100)) {
            throw new IllegalArgumentException(quality + " is not in range [0,100]");
        }
        this.quality = quality;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + quality;
    }
}
