package com.wix.mediaplatform.image.option.sharpen;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Float.parseFloat;

public class Sharpen extends Option {

    public static final String KEY = "shrp";

    private float radius;

    public Sharpen() {
        super(KEY);
    }

    public Sharpen(float radius) {
        super(KEY);
        if (!inRange(radius, 0, 1)) {
            throw new IllegalArgumentException(radius + " is not in range [0,1]");
        }
        this.radius = radius;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + DECIMAL_FORMAT.format(radius);
    }

    @Override
    public Option deserialize(String... params) {
        radius = parseFloat(params[0]);
        return this;
    }
}
