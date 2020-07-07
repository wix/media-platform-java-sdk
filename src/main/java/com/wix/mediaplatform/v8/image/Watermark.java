package com.wix.mediaplatform.v8.image;

import java.util.HashMap;
import java.util.Map;

public class Watermark {

    private String path;

    private int opacity = 50;

    private float proportions = 0.25f;

    private Gravity gravity = Gravity.CENTER;

    public String getPath() {
        return path;
    }

    public Watermark setPath(String path) {
        this.path = path;
        return this;
    }

    public int getOpacity() {
        return opacity;
    }

    public Watermark setOpacity(int opacity) {
        this.opacity = opacity;
        return this;
    }

    public float getProportions() {
        return proportions;
    }

    public Watermark setProportions(float proportions) {
        this.proportions = proportions;
        return this;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public Watermark setGravity(Gravity gravity) {
        this.gravity = gravity;
        return this;
    }

    public Map<String, Object> toClaims() {
        Map<String, Object> watermark = new HashMap<>();
        watermark.put("path", path);
        watermark.put("opacity", opacity);
        watermark.put("proportions", proportions);
        watermark.put("gravity", gravity.getGravity());

        Map<String, Object> claim = new HashMap<>();
        claim.put("wmk", watermark);

        return claim;
    }
}
