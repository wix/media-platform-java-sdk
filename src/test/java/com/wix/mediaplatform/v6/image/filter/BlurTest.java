package com.wix.mediaplatform.v6.image.filter;

import org.junit.Test;

public class BlurTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Blur(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Blur(-1);
    }

    @Test
    public void atLowerBound() {
        new Blur(0);
    }

    @Test
    public void atUpperBound() {
        new Blur(100);
    }

}