package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;

public class Fit extends Operation {

    private static String name = "fit";

    public Fit(String baseUrl, String fileId, String fileName, OriginalData originalData) {
        super(name, baseUrl, fileId, fileName, originalData);
    }

}
