package com.wix.mediaplatform.v8.image.encoder;

import com.wix.mediaplatform.v8.image.Option;

import static com.wix.mediaplatform.v8.image.StringToken.UNDERSCORE;
import static com.wix.mediaplatform.v8.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class JPEG extends Option {

    public static final String KEY = "q";

    private int quality;

    public JPEG() {
        super(KEY);
    }

    public JPEG(int quality) {
        super(KEY);
        if (!inRange(quality, 0, 100)) {
            throw new IllegalArgumentException(quality + " is not in range [0,100]");
        }
        this.quality = quality;
    }

    @Override
    public String serialize() {
        return KEY + UNDERSCORE + quality;
    }

    @Override
    public Option deserialize(String... params) {
        quality = parseInt(params[0]);
        return this;
    }
}
