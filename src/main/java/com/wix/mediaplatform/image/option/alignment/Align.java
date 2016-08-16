package com.wix.mediaplatform.image.option.alignment;

import com.wix.mediaplatform.image.option.Option;

public class Align extends Option {

    public static final String KEY = "al";

    private Alignment alignment;

    public Align() {
        super(KEY);
    }

    public Align(Alignment alignment) {
        super(KEY);
        this.alignment = alignment;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + alignment.getValue();
    }

    @Override
    public Option deserialize(String... params) {
        alignment = Alignment.fromString(params[0]);
        return this;
    }

    @Override
    public String toString() {
        return "Align{" +
                "alignment=" + alignment +
                '}';
    }
}
