package com.wix.mediaplatform.dto.image;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.FileDescriptor;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ImageDescriptor extends FileDescriptor {

    private int width;

    private int height;

    @SerializedName("file_input")
    private Analysis analysis;

    public ImageDescriptor() {
    }

    public ImageDescriptor(String parentFolderId, String hash, String originalFileName, String fileName, String fileUrl, long fileSize, String iconUrl, String mediaType, String mimeType, Set<String> labels, Set<String> tags, String status, long dateCreated, long dateModified, int width, int height, Analysis analysis) {
        super(parentFolderId, hash, originalFileName, fileName, fileUrl, fileSize, iconUrl, mediaType, mimeType, labels, tags, status, dateCreated, dateModified);
        this.width = width;
        this.height = height;
        this.analysis = analysis;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Analysis getAnalysis() {
        return analysis;
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

        @Override
        public String toString() {
            return "Square{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    public class Analysis {

        @SerializedName("face")
        private Set<Square> faces = newHashSet();

        public Analysis() {
        }

        public Analysis(Set<Square> faces) {
            this.faces = faces;
        }

        public Set<Square> getFaces() {
            return faces;
        }

        @Override
        public String toString() {
            return "Analysis{" +
                    "faces=" + faces +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImageDescriptor{" +
                "width=" + width +
                ", height=" + height +
                ", analysis=" + analysis +
                "} " + super.toString();
    }
}
