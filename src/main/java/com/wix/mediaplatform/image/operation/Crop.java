package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import org.jetbrains.annotations.Nullable;

public class Crop extends Operation<Crop> {

    private static final String NAME = "crop";

    private static final String KEY_X = "x";

    private static final String KEY_Y = "y";

    private static final String KEY_SCALE_FACTOR = "scl";

    private int x;

    private int y;

    private float scaleFactor;

    public Crop(String baseUrl, String fileId, String fileName, int width, int height, @Nullable OriginalData originalData, int x, int y, float scaleFactor) {
        super(NAME, baseUrl, fileId, fileName, width, height, originalData);
        this.x = x;
        this.y = y;
        this.scaleFactor = scaleFactor;
    }

    @Override
    protected String serialize() {
        return super.serialize() + COMMA + KEY_X + SEPARATOR + x + COMMA + KEY_Y + SEPARATOR + y
                + COMMA + KEY_SCALE_FACTOR + SEPARATOR + scaleFactor;
    }
}
