package com.wix.mediaplatform.image.filter;

import org.junit.Test;

public class ContrastTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Contrast(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Contrast(-101);
    }

    @Test
    public void atLowerBound() {
        new Contrast(-100);
    }

    @Test
    public void atUpperBound() {
        new Contrast(100);
    }

}