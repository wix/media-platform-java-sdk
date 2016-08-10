package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;

public class Crop extends Operation {

    private static String name = "crop";

    public Crop(String baseUrl, String fileId, String fileName, OriginalData originalData) {
        super(name, baseUrl, fileId, fileName, originalData);
    }

}
