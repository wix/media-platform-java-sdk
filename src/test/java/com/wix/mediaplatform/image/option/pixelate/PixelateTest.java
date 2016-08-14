package com.wix.mediaplatform.image.option.pixelate;

import org.junit.Test;

public class PixelateTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Pixelate(-1);
    }

    @Test
    public void atLowerBound() {
        new Pixelate(0);
    }
}