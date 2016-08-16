package com.wix.mediaplatform.image.option.blur;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class Blur extends Option {

    public static final String KEY = "blur";

    private int percentage;

    public Blur() {
        super(KEY);
    }

    public Blur(int percentage) {
        super(KEY);
        if (!inRange(percentage, 0, 100)) {
            throw new IllegalArgumentException(percentage + " is not in range [0,100]");
        }
        this.percentage = percentage;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + percentage;
    }

    @Override
    public Option deserialize(String... params) {
        percentage = parseInt(params[0]);
        return this;
    }

    @Override
    public String toString() {
        return "Blur{" +
                "percentage=" + percentage +
                '}';
    }
}
