package com.wix.mediaplatform.v8.image.filter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlurTest {

    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blur(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blur(-1)
        );
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