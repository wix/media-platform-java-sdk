package com.wix.mediaplatform.image.framing;

import static com.wix.mediaplatform.image.StringToken.*;

public class Fit implements Frame {

    public static final String NAME = "fit";

    private int width;

    private int height;

    public Fit(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String serialize() {
        return NAME + FORWARD_SLASH + KEY_WIDTH + UNDERSCORE + width + COMMA + KEY_HEIGHT + UNDERSCORE + height;
    }
}
