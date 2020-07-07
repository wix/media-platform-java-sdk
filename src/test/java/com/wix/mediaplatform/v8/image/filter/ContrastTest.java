package com.wix.mediaplatform.v8.image.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContrastTest {

    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contrast(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contrast(-101)
        );
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