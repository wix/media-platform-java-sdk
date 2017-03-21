package com.wix.mediaplatform.image.filter;

import org.junit.Test;

public class BrightnessTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Brightness(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Brightness(-101);
    }

    @Test
    public void atLowerBound() {
        new Brightness(-100);
    }

    @Test
    public void atUpperBound() {
        new Brightness(100);
    }
}