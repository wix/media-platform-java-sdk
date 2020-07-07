package com.wix.mediaplatform.v8.image.parser;

import com.wix.mediaplatform.v8.image.Image;
import com.wix.mediaplatform.v8.image.Metadata;
import com.wix.mediaplatform.v8.metadata.FileMetadata;
import com.wix.mediaplatform.v8.metadata.basic.ImageBasicMetadata;

public class FileMetadataParser {

    public static void parse(Image image, FileMetadata fileMetadata) {
        FileDescriptorParser.parse(image, fileMetadata.getFileDescriptor());

        if (fileMetadata.getBasic() != null) {
            ImageBasicMetadata basicMetadata = (ImageBasicMetadata) fileMetadata.getBasic();
            Metadata metadata = new Metadata(
                    basicMetadata.getWidth(),
                    basicMetadata.getHeight(),
                    fileMetadata.getFileDescriptor().getMimeType()
            );
            image.setMetadata(metadata);
        }
    }
}
