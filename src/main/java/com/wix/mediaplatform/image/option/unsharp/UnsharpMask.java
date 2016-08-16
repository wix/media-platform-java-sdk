package com.wix.mediaplatform.image.option.unsharp;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Float.parseFloat;

public class UnsharpMask extends Option {

    public static final String KEY = "usm";

    private float radius;

    private float amount;

    private float threshold;

    public UnsharpMask() {
        super(KEY);
    }

    public UnsharpMask(float radius, float amount, float threshold) {
        super(KEY);

        if (!inRange(radius, 0.1f, 500f)) {
            throw new IllegalArgumentException(radius + " is not in range [0.1,500]");
        }
        if (!inRange(amount, 0, 10)) {
            throw new IllegalArgumentException(amount + " is not in range [0,10]");
        }
        if (!inRange(threshold, 0, 255)) {
            throw new IllegalArgumentException(threshold + " is not in range [0,255]");
        }

        this.radius = radius;
        this.amount = amount;
        this.threshold = threshold;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + DECIMAL_FORMAT.format(radius) + SEPARATOR + DECIMAL_FORMAT.format(amount) + SEPARATOR + DECIMAL_FORMAT.format(threshold);
    }

    @Override
    public Option deserialize(String... params) {
        radius = parseFloat(params[0]);
        amount = parseFloat(params[1]);
        threshold = parseFloat(params[2]);
        return this;
    }
}
