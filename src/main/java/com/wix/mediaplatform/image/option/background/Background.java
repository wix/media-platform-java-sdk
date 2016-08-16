package com.wix.mediaplatform.image.option.background;

import com.wix.mediaplatform.image.option.Option;

import java.util.regex.Pattern;

public class Background extends Option {

    private static final Pattern hexPattern = Pattern.compile("[0-9a-f]{6}");

    public static final String KEY = "c";

    private String color;

    public Background() {
        super(KEY);
    }

    public Background(String color) {
        super(KEY);
        if (!hexPattern.matcher(color).matches()) {
            throw new IllegalArgumentException(color + " is not a valid hex color");
        }
        this.color = color;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + color;
    }

    @Override
    public Option deserialize(String... params) {
        color = params[0];
        return this;
    }

    @Override
    public String toString() {
        return "Background{" +
                "color='" + color + '\'' +
                '}';
    }
}
