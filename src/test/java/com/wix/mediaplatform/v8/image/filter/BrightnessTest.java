package com.wix.mediaplatform.v8.image.filter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrightnessTest {

    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Brightness(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Brightness(-101)
        );
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