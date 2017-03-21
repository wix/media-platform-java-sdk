package com.wix.mediaplatform.image.filter;

import com.wix.mediaplatform.image.Option;

import static com.wix.mediaplatform.image.StringToken.UNDERSCORE;
import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class Brightness extends Option {

    public static final String KEY = "br";

    private int brightness;

    public Brightness() {
        super(KEY);
    }

    public Brightness(int brightness) {
        super(KEY);
        if (!inRange(brightness, -100, 100)) {
            throw new IllegalArgumentException(brightness + " is not in range [-100,100]");
        }
        this.brightness = brightness;
    }

    @Override
    public String serialize() {
        return KEY + UNDERSCORE + brightness;
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
