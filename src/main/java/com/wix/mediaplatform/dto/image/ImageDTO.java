package com.wix.mediaplatform.dto.image;

import com.wix.mediaplatform.dto.FileBaseDTO;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ImageDTO extends FileBaseDTO {

    private int width;

    private int height;

    private Set<Square> faces = newHashSet();

    public ImageDTO() {
    }

    public ImageDTO(String parentFolderId, String hash, String originalFileName, String fileName, String fileUrl, long fileSize, String iconUrl, String mediaType, String mimeType, Set<String> lables, Set<String> tags, long dateCreated, long dateModified, int width, int height, Set<Square> faces) {
        super(parentFolderId, hash, originalFileName, fileName, fileUrl, fileSize, iconUrl, mediaType, mimeType, lables, tags, dateCreated, dateModified);
        this.width = width;
        this.height = height;
        this.faces = faces;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Square> getFaces() {
        return faces;
    }

    public class Square {
        private int x;
        private int y;
        private int width;
        private int height;

        public Square() {
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "width=" + width +
                ", height=" + height +
                ", faces=" + faces +
                "} " + super.toString();
    }
}
