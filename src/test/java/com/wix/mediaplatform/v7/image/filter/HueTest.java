package com.wix.mediaplatform.v7.image.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HueTest {

    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Hue(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Hue(-101)
        );
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