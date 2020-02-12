package com.wix.mediaplatform.v7.image.filter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaturationTest {

    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Saturation(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Saturation(-101)
        );
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