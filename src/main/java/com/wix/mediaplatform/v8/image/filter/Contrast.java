package com.wix.mediaplatform.v8.image.filter;

import com.wix.mediaplatform.v8.image.Option;
import com.wix.mediaplatform.v8.image.StringToken;
import com.wix.mediaplatform.v8.image.Validation;

import static java.lang.Integer.parseInt;

public class Contrast extends Option {

    public static final String KEY = "con";

    private int contrast;

    public Contrast() {
        super(KEY);
    }

    public Contrast(int contrast) {
        super(KEY);
        if (!Validation.inRange(contrast, -100, 100)) {
            throw new IllegalArgumentException(contrast + " is not in range [-100,100]");
        }
        this.contrast = contrast;
    }

    @Override
    public String serialize() {
        return KEY + StringToken.UNDERSCORE + contrast;
    }

    @Override
    public Option deserialize(String... params) {
        contrast = parseInt(params[0]);
        return this;
    }

    @Override
    public String toString() {
        return "Contrast{" +
                "contrast=" + contrast +
                '}';
    }
}
