package com.wix.mediaplatform.v6.image.framing;

import com.wix.mediaplatform.v6.image.StringToken;

public class Fit implements Frame {

    public static final String NAME = "fit";

    private int width;

    private int height;

    public Fit(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String serialize() {
        return NAME + StringToken.FORWARD_SLASH + StringToken.KEY_WIDTH + StringToken.UNDERSCORE + width + StringToken.COMMA + StringToken.KEY_HEIGHT + StringToken.UNDERSCORE + height;
    }
}
