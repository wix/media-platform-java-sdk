package com.wix.mediaplatform.v8.service.video;

import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.Specification;

public class ExtractStoryboardSpecification implements Specification {

    private Destination destination;

    private int columns;

    private int rows;

    private int tileWidth;

    private int tileHeight;

    private String format = "jpg";

    private float segmentDuration;

    public ExtractStoryboardSpecification() {
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractStoryboardSpecification setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public int getColumns() {
        return columns;
    }

    public ExtractStoryboardSpecification setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    public int getRows() {
        return rows;
    }

    public ExtractStoryboardSpecification setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public ExtractStoryboardSpecification setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
        return this;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public ExtractStoryboardSpecification setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public ExtractStoryboardSpecification setFormat(String format) {
        this.format = format;
        return this;
    }

    public float getSegmentDuration() {
        return segmentDuration;
    }

    public ExtractStoryboardSpecification setSegmentDuration(float segmentDuration) {
        this.segmentDuration = segmentDuration;
        return this;
    }
}
