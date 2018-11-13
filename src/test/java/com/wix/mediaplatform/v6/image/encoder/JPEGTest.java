package com.wix.mediaplatform.v6.image.encoder;

import org.junit.Test;

public class JPEGTest {
    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new JPEG(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new JPEG(-1);
    }

    @Test
    public void atLowerBound() {
        new JPEG(0);
    }

    @Test
    public void atUpperBound() {
        new JPEG(100);
    }
}