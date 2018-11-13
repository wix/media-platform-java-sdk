package com.wix.mediaplatform.v6.image.parser;

import com.wix.mediaplatform.v6.dto.metadata.FileMetadata;
import com.wix.mediaplatform.v6.dto.metadata.basic.ImageBasicMetadata;
import com.wix.mediaplatform.v6.image.Image;
import com.wix.mediaplatform.v6.image.Metadata;

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
