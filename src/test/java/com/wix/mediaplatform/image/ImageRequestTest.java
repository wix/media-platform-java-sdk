package com.wix.mediaplatform.image;

import com.wix.mediaplatform.image.option.alignment.Alignment;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by elad on 14/08/2016.
 * <p>
 * Wix.com
 */
public class ImageRequestTest {

    @Test
    public void fitWithAllOptions() throws Exception {
        ImageRequest imageRequest = new ImageRequest("domain.com/user/bucket", "fileId", "fileName", null);
        String url = imageRequest.fit(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(10).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fit/h_100,w_100,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_10,usm_10_10_10/fileName"));
    }

    @Test
    public void fillWithAllOptions() throws Exception {
        ImageRequest imageRequest = new ImageRequest("domain.com/user/bucket", "fileId", "fileName", null);
        String url = imageRequest.fill(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(10).unsharpMask(10, 10, 10)
                .baseline().upscale().quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/h_100,w_100,al_fs,bl,blur_10,br_10,con_10,eye,hue_10,lg,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_10,usm_10_10_10/fileName"));
    }

    @Test
    public void cropWithAllOptions() throws Exception {
        ImageRequest imageRequest = new ImageRequest("domain.com/user/bucket", "fileId", "fileName", null);
        String url = imageRequest.crop(100, 100, 1, 1, 1).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(10).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/crop/h_100,w_100,x_1,y_1,scl_1.0,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_10,usm_10_10_10/fileName"));
    }

    @Test
    public void canvasWithAllOptions() throws Exception {
        ImageRequest imageRequest = new ImageRequest("domain.com/user/bucket", "fileId", "fileName", null);
        String url = imageRequest.canvas(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(10).unsharpMask(10, 10, 10)
                .baseline().background("aabbcc").quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/canvas/h_100,w_100,al_fs,bl,blur_10,br_10,c_aabbcc,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_10,usm_10_10_10/fileName"));
    }

}