package com.wix.mediaplatform.v6.image.filter;

import org.junit.Test;

public class UnsharpMaskTest {

    @Test(expected = IllegalArgumentException.class)
    public void radiusOutOfUpperBound() {
        new UnsharpMask(501, 5, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void radiusOutOfLowerBound() {
        new UnsharpMask(0, 5, 100);
    }

    @Test
    public void radiusAtLowerBound() {
        new UnsharpMask(0.1f, 5, 100);
    }

    @Test
    public void radiusAtUpperBound() {
        new UnsharpMask(500, 5, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void amountOutOfUpperBound() {
        new UnsharpMask(100, 11, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void amountOutOfLowerBound() {
        new UnsharpMask(100, -1, 100);
    }

    @Test
    public void amountAtLowerBound() {
        new UnsharpMask(100, 0, 100);
    }

    @Test
    public void amountAtUpperBound() {
        new UnsharpMask(100, 10, 100);
    }


    @Test(expected = IllegalArgumentException.class)
    public void thresholdOutOfUpperBound() {
        new UnsharpMask(100, 5, 256);
    }

    @Test(expected = IllegalArgumentException.class)
    public void thresholdOutOfLowerBound() {
        new UnsharpMask(100, 5, -1);
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