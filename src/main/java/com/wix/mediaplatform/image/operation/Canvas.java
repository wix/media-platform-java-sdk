package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import org.jetbrains.annotations.Nullable;

public class Canvas extends Operation {

    private static final String NAME = "canvas";

    public Canvas(String baseUrl, String fileId, String fileName, int width, int height, @Nullable OriginalData originalData) {
        super(NAME, baseUrl, fileId, fileName, width, height, originalData);
    }
}
