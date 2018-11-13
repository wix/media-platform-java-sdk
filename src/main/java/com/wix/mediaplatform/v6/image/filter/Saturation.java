package com.wix.mediaplatform.v6.image.filter;

import com.wix.mediaplatform.v6.image.Option;
import com.wix.mediaplatform.v6.image.StringToken;
import com.wix.mediaplatform.v6.image.Validation;

import static java.lang.Integer.parseInt;

public class Saturation extends Option {

    public static final String KEY = "sat";

    private int saturation;

    public Saturation() {
        super(KEY);
    }

    public Saturation(int saturation) {
        super(KEY);
        if (!Validation.inRange(saturation, -100, 100)) {
            throw new IllegalArgumentException(saturation + " is not in range [-100,100]");
        }
        this.saturation = saturation;
    }

    @Override
    public String serialize() {
        return KEY + StringToken.UNDERSCORE + saturation;
    }

    @Override
    public Option deserialize(String... params) {
        saturation = parseInt(params[0]);
        return this;
    }
}
