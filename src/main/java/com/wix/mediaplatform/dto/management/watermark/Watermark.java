package com.wix.mediaplatform.dto.management.watermark;

import com.google.gson.annotations.SerializedName;

public class Watermark<T extends Watermark<T>> {

    @SerializedName("wm")
    private String watermark;

    @SerializedName("wmop")
    private Integer opacity;

    @SerializedName("wmpos")
    private Gravity gravity;

    @SerializedName("wmscl")
    private Float scale;

    public Watermark() {
    }

    public T setWatermark(String watermark) {
        this.watermark = watermark;
        return (T) this;
    }

    public T setOpacity(Integer opacity) {
        this.opacity = opacity;
        return (T) this;
    }

    public T setGravity(Gravity gravity) {
        this.gravity = gravity;
        return (T) this;
    }

    public T setScale(Float scale) {
        this.scale = scale;
        return (T) this;
    }

    public String getWatermark() {
        return watermark;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public Float getScale() {
        return scale;
    }
}
