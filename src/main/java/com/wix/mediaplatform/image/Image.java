package com.wix.mediaplatform.image;

import com.wix.mediaplatform.image.operation.Canvas;
import com.wix.mediaplatform.image.operation.Crop;
import com.wix.mediaplatform.image.operation.Fill;
import com.wix.mediaplatform.image.operation.Fit;
import org.jetbrains.annotations.Nullable;

public class Image {

    private String baseUrl;

    private String fileId;

    private String fileName;

    private Metadata metadata;

    public Image(String baseUrl, String fileId, String fileName, @Nullable Metadata metadata) {
        this.baseUrl = baseUrl;
        this.fileId = fileId;
        this.fileName = fileName;
        this.metadata = metadata;
    }

    public Fit fit(int width, int height) {
        return new Fit(baseUrl, fileId, fileName, width, height, metadata);
    }

    public Fill fill(int width, int height) {
        return new Fill(baseUrl, fileId, fileName, width, height, metadata);
    }

    public Crop crop(int width, int height, int x, int y, float scaleFactor) {
        return new Crop(baseUrl, fileId, fileName, width, height, metadata, x, y, scaleFactor);
    }

    public Canvas canvas(int width, int height) {
        return new Canvas(baseUrl, fileId, fileName, width, height, metadata);
    }

    @Override
    public String toString() {
        return "Image{" +
                "baseUrl='" + baseUrl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
