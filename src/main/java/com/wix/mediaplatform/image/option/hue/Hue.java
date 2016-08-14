package com.wix.mediaplatform.image.option.hue;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;

public class Hue extends Option {

    private static final String KEY = "hue";

    private int hue;

    public Hue(int hue) {
        super(KEY);
        if (!inRange(hue, -100, 100)) {
            throw new IllegalArgumentException(hue + " is not in range [-100,100]");
        }
        this.hue = hue;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + hue;
    }
}
