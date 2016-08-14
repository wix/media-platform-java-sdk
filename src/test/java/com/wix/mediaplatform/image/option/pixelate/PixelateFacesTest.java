package com.wix.mediaplatform.image.option.pixelate;

import org.junit.Test;

public class PixelateFacesTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfLowerBound() {
        new PixelateFaces(-1);
    }

    @Test
    public void atLowerBound() {
        new PixelateFaces(0);
    }
}