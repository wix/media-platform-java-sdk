package com.wix.mediaplatform.image.filter;

import org.junit.Test;

public class SaturationTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Saturation(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Saturation(-101);
    }

    @Test
    public void atLowerBound() {
        new Saturation(-100);
    }

    @Test
    public void atUpperBound() {
        new Saturation(100);
    }

}