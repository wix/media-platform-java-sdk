package com.wix.mediaplatform.v8.geometry;

public class Rectangle {

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    public Rectangle() {
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
