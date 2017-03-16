package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.Metadata;
import org.jetbrains.annotations.Nullable;

public class Fit extends Operation<Fit> {

    private static final String NAME = "fit";

    public Fit(String baseUrl, String fileId, String fileName, int width, int height, @Nullable Metadata metadata) {
        super(NAME, baseUrl, fileId, fileName, width, height, metadata);
    }

}
