package com.wix.mediaplatform.image.option.background;

import org.junit.Test;

public class BackgroundTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidHEXColor() {
        new Background("zzzzzz");
    }

    @Test
    public void validHEXColor() {
        new Background("0099ff");
    }

}