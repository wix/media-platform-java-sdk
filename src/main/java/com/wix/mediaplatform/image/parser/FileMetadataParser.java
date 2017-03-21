package com.wix.mediaplatform.image.parser;

import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.metadata.basic.ImageBasicMetadata;
import com.wix.mediaplatform.image.Image;
import com.wix.mediaplatform.image.Metadata;

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
