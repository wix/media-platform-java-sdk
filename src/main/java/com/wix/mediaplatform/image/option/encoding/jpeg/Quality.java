package com.wix.mediaplatform.image.option.encoding.jpeg;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class Quality extends Option {

    public static final String KEY = "q";

    private int quality;

    public Quality() {
        super(KEY);
    }

    public Quality(int quality) {
        super(KEY);
        if (!inRange(quality, 0, 100)) {
            throw new IllegalArgumentException(quality + " is not in range [0,100]");
        }
        this.quality = quality;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + quality;
    }

    @Override
    public Option deserialize(String... params) {
        quality = parseInt(params[0]);
        return this;
    }

    @Override
    public String toString() {
        return "Quality{" +
                "quality=" + quality +
                '}';
    }
}
