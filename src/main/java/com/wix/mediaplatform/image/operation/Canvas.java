package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import com.wix.mediaplatform.image.option.alignment.Align;
import com.wix.mediaplatform.image.option.alignment.Alignment;
import com.wix.mediaplatform.image.option.background.Background;
import org.jetbrains.annotations.Nullable;

public class Canvas extends Operation<Canvas> {

    private static final String NAME = "canvas";

    public Canvas(String baseUrl, String fileId, String fileName, int width, int height, @Nullable OriginalData originalData) {
        super(NAME, baseUrl, fileId, fileName, width, height, originalData);
    }

    public Canvas alignment(Alignment alignment) {
        options.add(new Align(alignment));
        return this;
    }

    public Canvas background(String color) {
        options.add(new Background(color));
        return this;
    }
}
