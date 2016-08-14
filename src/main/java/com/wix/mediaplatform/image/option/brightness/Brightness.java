package com.wix.mediaplatform.image.option.brightness;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;

public class Brightness extends Option {

    private static final String KEY = "br";

    private int brightness;

    public Brightness(int brightness) {
        super(KEY);
        if (!inRange(brightness, -100, 100)) {
            throw new IllegalArgumentException(brightness + " is not in range [-100,100]");
        }
        this.brightness = brightness;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + brightness;
    }
}
