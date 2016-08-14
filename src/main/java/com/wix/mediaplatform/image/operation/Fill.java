package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import com.wix.mediaplatform.image.option.alignment.Align;
import com.wix.mediaplatform.image.option.alignment.Alignment;
import com.wix.mediaplatform.image.option.upscale.Upscale;
import org.jetbrains.annotations.Nullable;

public class Fill extends Operation<Fill> {

    private static final String NAME = "fill";

    public Fill(String baseUrl, String fileId, String fileName, int width, int height, @Nullable OriginalData originalData) {
        super(NAME, baseUrl, fileId, fileName, width, height, originalData);
    }

    public Fill alignment(Alignment alignment) {
        options.add(new Align(alignment));
        return this;
    }

    public Fill upscale() {
        options.add(new Upscale());
        return this;
    }
}
