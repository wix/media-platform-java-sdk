package com.wix.mediaplatform.v8.metadata.features;

public class Color {

    private Integer r;

    private Integer g;

    private Integer b;

    private Float pixelFraction;

    private Float score;

    public Color() {
    }

    public Integer getR() {
        return r;
    }

    public Integer getG() {
        return g;
    }

    public Integer getB() {
        return b;
    }

    public Float getPixelFraction() {
        return pixelFraction;
    }

    public Float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", pixelFraction=" + pixelFraction +
                ", score=" + score +
                '}';
    }
}
