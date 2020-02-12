package com.wix.mediaplatform.v7.image.framing;

import static com.wix.mediaplatform.v7.image.StringToken.*;

public class Crop implements Frame {

    public static final String NAME = "crop";

    public static final String KEY_X = "x";

    public static final String KEY_Y = "y";

    public static final String KEY_SCALE = "scl";

    private int x;

    private int y;

    private int width;

    private int height;

    private float scale;

    public Crop(int x, int y, int width, int height, float scale) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public String serialize() {
        return NAME + FORWARD_SLASH + KEY_WIDTH + UNDERSCORE + width + COMMA + KEY_HEIGHT + UNDERSCORE + height + COMMA + KEY_X + UNDERSCORE + x + COMMA + KEY_Y + UNDERSCORE + y
                + COMMA + KEY_SCALE + UNDERSCORE + scale;
    }
}
