package com.wix.mediaplatform.v8.image;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @Test
    void toClaimsWithCacheableUntil() {
        Long expiration = 1000L;
        Policy policy = new Policy()
                .setMaxHeight(1000)
                .setMaxWidth(1500)
                .setPath("/path/to/image.jpg");
        ImageToken token = new ImageToken().setPolicy(policy).cacheableUntil(expiration);

        Map<String, Object> claims = token.toClaims();

        //noinspection unchecked
        assertThat((List<String>) claims.get("aud"), hasItem("urn:service:image.operations"));
        assertThat(claims.get("iat"), nullValue());
        assertThat(claims.get("jti"), is(expiration.toString()));
        assertThat(claims.get("exp"), is(expiration));
    }
}