package com.wix.mediaplatform.v7.image;

public class Validation {

    public static boolean inRange(int value, int lowerInclusive, int upperInclusive) {
        return value >= lowerInclusive && value <= upperInclusive;
    }

    public static boolean inRange(float value, float lowerInclusive, float upperInclusive) {
        return value >= lowerInclusive && value <= upperInclusive;
    }

    public static boolean greaterThan(int value, int lowerInclusive) {
        return value >= lowerInclusive;
    }
}
