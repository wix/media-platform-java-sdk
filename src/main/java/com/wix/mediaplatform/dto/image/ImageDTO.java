package com.wix.mediaplatform.dto.image;

import com.wix.mediaplatform.dto.BaseDTO;

import java.util.Set;

public class ImageDTO extends BaseDTO {

    private int width;

    private int height;

    private Set<Square> faces;

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
}
