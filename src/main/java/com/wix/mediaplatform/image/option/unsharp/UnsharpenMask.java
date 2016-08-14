package com.wix.mediaplatform.image.option.unsharp;

import com.wix.mediaplatform.image.option.Option;

public class UnsharpenMask extends Option {

    private static final String KEY = "usm";

    private float radius;

    private float amount;

    private float threshold;

    public UnsharpenMask(float radius, float amount, float threshold) {
        super(KEY);
        this.radius = radius;
        this.amount = amount;
        this.threshold = threshold;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + DECIMAL_FORMAT.format(radius) + SEPARATOR + DECIMAL_FORMAT.format(amount) + SEPARATOR + DECIMAL_FORMAT.format(threshold);
    }
}
