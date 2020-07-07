package com.wix.mediaplatform.v8.image.encoder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JPEGTest {
    @Test
    public void outOfUpperBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new JPEG(101)
        );
    }

    @Test
    public void outOfLowerBound() {
        assertThrows(IllegalArgumentException.class, () ->
                new JPEG(-1)
        );
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