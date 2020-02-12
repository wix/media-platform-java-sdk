package com.wix.mediaplatform.v7.image.framing;

import static com.wix.mediaplatform.v7.image.StringToken.*;

public class Fill implements Frame {

    public static final String NAME = "fill";

    private int width;

    private int height;

    public Fill(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String serialize() {
        return NAME + FORWARD_SLASH + KEY_WIDTH + UNDERSCORE + width + COMMA + KEY_HEIGHT + UNDERSCORE + height;
    }
}
