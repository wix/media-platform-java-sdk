package com.wix.mediaplatform.v7.image.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnsharpMaskTest {

    @Test
    public void radiusOutOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(501, 5, 100)
        );
    }

    @Test
    public void radiusOutOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(0, 5, 100)
        );
    }

    @Test
    public void radiusAtLowerBound() {
        new UnsharpMask(0.1f, 5, 100);
    }

    @Test
    public void radiusAtUpperBound() {
        new UnsharpMask(500, 5, 100);
    }

    @Test
    public void amountOutOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(100, 11, 100)
        );
    }

    @Test
    public void amountOutOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(100, -1, 100)
        );
    }

    @Test
    public void amountAtLowerBound() {
        new UnsharpMask(100, 0, 100);
    }

    @Test
    public void amountAtUpperBound() {
        new UnsharpMask(100, 10, 100);
    }


    @Test
    public void thresholdOutOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(100, 5, 256)
        );
    }

    @Test
    public void thresholdOutOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new UnsharpMask(100, 5, -1)
        );
    }

    @Test
    public void thresholdAtLowerBound() {
        new UnsharpMask(100, 5, 0);
    }

    @Test
    public void thresholdAtUpperBound() {
        new UnsharpMask(100, 5, 255);
    }
}