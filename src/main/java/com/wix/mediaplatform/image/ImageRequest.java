package com.wix.mediaplatform.image;

import com.wix.mediaplatform.image.operation.Canvas;
import com.wix.mediaplatform.image.operation.Crop;
import com.wix.mediaplatform.image.operation.Fill;
import com.wix.mediaplatform.image.operation.Fit;
import org.jetbrains.annotations.Nullable;

public class ImageRequest {

    private String baseUrl;

    private String fileId;

    private String fileName;

    private OriginalData originalData;

    public ImageRequest(String baseUrl, String fileId, String fileName, @Nullable OriginalData originalData) {
        this.baseUrl = baseUrl;
        this.fileId = fileId;
        this.fileName = fileName;
        this.originalData = originalData;
    }

    public Fit fit(int width, int height) {
        return new Fit(baseUrl, fileId, fileName, width, height, originalData);
    }

    public Fill fill(int width, int height) {
        return new Fill(baseUrl, fileId, fileName, width, height, originalData);
    }

    public Crop crop(int width, int height, int x, int y, float scaleFactor) {
        return new Crop(baseUrl, fileId, fileName, width, height, originalData, x, y, scaleFactor);
    }

    public Canvas canvas(int width, int height) {
        return new Canvas(baseUrl, fileId, fileName, width, height, originalData);
    }
}
