package com.wix.mediaplatform.v6.image.parser;

import com.wix.mediaplatform.v6.image.Image;
import com.wix.mediaplatform.v6.service.FileDescriptor;


public class FileDescriptorParser {

    public static void parse(Image image, FileDescriptor fileDescriptor) {
        String type = fileDescriptor.getMimeType().split("/")[0].toLowerCase();
        if (!type.equals("image")) {
            throw new IllegalArgumentException("not an image file descriptor");
        }

        image.setPath(fileDescriptor.getPath());
        image.setFileName(fileDescriptor.getPath().substring(
                fileDescriptor.getPath().lastIndexOf('/') + 1)
        );
    }
}
