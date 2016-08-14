package com.wix.mediaplatform.image.option.encoding.jpeg;

import org.junit.Test;

public class QualityTest {
    @Test(expected = IllegalArgumentException.class)
    public void outOfUpperBound() {
        new Quality(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new Quality(-1);
    }

    @Test
    public void atLowerBound() {
        new Quality(0);
    }

    @Test
    public void atUpperBound() {
        new Quality(100);
    }
}