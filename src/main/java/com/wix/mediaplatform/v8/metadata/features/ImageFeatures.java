package com.wix.mediaplatform.v8.metadata.features;

import com.wix.mediaplatform.v8.geometry.Rectangle;

public class ImageFeatures extends Features {

    private Label[] labels;

    private Rectangle[] faces;

    private Color[] colors;

    private ExplicitContent[] explicitContent;

    public ImageFeatures() {
    }

    public Label[] getLabels() {
        return labels;
    }

    public ImageFeatures setLabels(Label[] labels) {
        this.labels = labels;
        return this;
    }

    public Rectangle[] getFaces() {
        return faces;
    }

    public ImageFeatures setFaces(Rectangle[] faces) {
        this.faces = faces;
        return this;
    }

    public Color[] getColors() {
        return colors;
    }

    public ImageFeatures setColors(Color[] colors) {
        this.colors = colors;
        return this;
    }

    public ExplicitContent[] getExplicitContent() {
        return explicitContent;
    }

    public ImageFeatures setExplicitContent(ExplicitContent[] explicitContent) {
        this.explicitContent = explicitContent;
        return this;
    }
}
