package com.wix.mediaplatform.dto.management.watermark;

import com.google.gson.annotations.SerializedName;

public enum Gravity {

    @SerializedName("1")
    NorthWest (1),
    @SerializedName("2")
    North (2),
    @SerializedName("3")
    NorthEast (3),
    @SerializedName("4")
    West (4),
    @SerializedName("5")
    Center (5),
    @SerializedName("6")
    East (6),
    @SerializedName("7")
    SouthWest (7),
    @SerializedName("8")
    South (8),
    @SerializedName("9")
    SouthEast (9),
    @SerializedName("10")
    Texture (10);

    private int gravity;

    Gravity(int gravity) {
        this.gravity = gravity;
    }

    public int getGravity() {
        return gravity;
    }
}
