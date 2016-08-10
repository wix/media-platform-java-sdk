package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;

public class Fill extends Operation {

    private static String name = "fill";

    public Fill(String baseUrl, String fileId, String fileName, OriginalData originalData) {
        super(name, baseUrl, fileId, fileName, originalData);
    }

}
