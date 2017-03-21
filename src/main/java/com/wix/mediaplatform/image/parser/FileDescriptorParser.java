package com.wix.mediaplatform.image.parser;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.image.Image;


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
