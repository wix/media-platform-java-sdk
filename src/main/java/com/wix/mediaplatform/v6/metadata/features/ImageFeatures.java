package com.wix.mediaplatform.v6.metadata.features;

import com.wix.mediaplatform.v6.geometry.Rectangle;

import java.util.Arrays;

public class ImageFeatures extends Features {

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
