package com.wix.mediaplatform.image.option.hue;

import org.junit.Test;

public class HueTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Hue(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Hue(-101);
    }

    @Test
    public void atLowerBound() {
        new Hue(-100);
    }

    @Test
    public void atUpperBound() {
        new Hue(100);
    }
}