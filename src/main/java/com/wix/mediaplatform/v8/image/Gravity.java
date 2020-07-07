package com.wix.mediaplatform.v8.image;

public enum Gravity {
    CENTER("center"),
    NORTH("north"),
    NORTH_WEST("north-west"),
    WEST("west"),
    SOUTH_WEST("south-west"),
    SOUTH("south"),
    SOUTH_EAST("south-east"),
    EAST("east"),
    NORTH_EAST("north-east");

    private final String gravity;

    Gravity(String gravity) {
        this.gravity = gravity;
    }

    public String getGravity() {
        return gravity;
    }
}
