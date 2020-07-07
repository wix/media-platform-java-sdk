package com.wix.mediaplatform.v8.image;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by elad on 12/02/2020.
 * Wix.com
 */
class ImageTokenTest {

    @Test
    void toClaims() {
        Policy policy = new Policy()
                .setMaxHeight(1000)
                .setMaxWidth(1500)
                .setPath("/path/to/image.jpg");
        Watermark watermark = new Watermark()
                .setPath("/path/to/mark.png")
                .setGravity(Gravity.SOUTH)
                .setOpacity(10)
                .setProportions(20);
        ImageToken token = new ImageToken().setPolicy(policy).setWatermark(watermark);

        Map<String, Object> claims = token.toClaims();

        //noinspection unchecked
        assertThat((List<String>) claims.get("aud"), hasItem("urn:service:image.operations"));
        assertThat(((Map<String, Object>) claims.get("wmk")).get("gravity"), equalTo("south"));
    }
}