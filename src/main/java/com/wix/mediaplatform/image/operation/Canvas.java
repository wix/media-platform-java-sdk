package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;

public class Canvas extends Operation {

    private static String name = "canvas";

    public Canvas(String baseUrl, String fileId, String fileName, OriginalData originalData) {
        super(name, baseUrl, fileId, fileName, originalData);
    }

}
