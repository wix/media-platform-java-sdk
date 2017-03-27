package com.wix.mediaplatform.dto.metadata.features;

import com.wix.mediaplatform.geometry.Rectangle;

import java.util.Arrays;

public class ImageFeatures implements Features {

    private Label[] label;

    private Rectangle[] faces;

    private Color[] colors;

    @Override
    public String toString() {
        return "ImageFeatures{" +
                "label=" + Arrays.toString(label) +
                ", faces=" + Arrays.toString(faces) +
                ", colors=" + Arrays.toString(colors) +
                '}';
    }
}
