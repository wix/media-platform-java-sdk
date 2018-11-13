package com.wix.mediaplatform.v6.image.filter;

import com.wix.mediaplatform.v6.image.Option;
import com.wix.mediaplatform.v6.image.StringToken;
import com.wix.mediaplatform.v6.image.Validation;

import static java.lang.Integer.parseInt;

public class Brightness extends Option {

    public static final String KEY = "br";

    private int brightness;

    public Brightness() {
        super(KEY);
    }

    public Brightness(int brightness) {
        super(KEY);
        if (!Validation.inRange(brightness, -100, 100)) {
            throw new IllegalArgumentException(brightness + " is not in range [-100,100]");
        }
        this.brightness = brightness;
    }

    @Override
    public String serialize() {
        return KEY + StringToken.UNDERSCORE + brightness;
    }

    @Override
    public Option deserialize(String... params) {
        brightness = parseInt(params[0]);
        return this;
    }

    @Override
    public String toString() {
        return "Brightness{" +
                "brightness=" + brightness +
                '}';
    }
}
