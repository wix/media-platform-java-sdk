package com.wix.mediaplatform.image;

import com.wix.mediaplatform.image.option.alignment.Alignment;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ImageTest {

    private Image image = new Image("domain.com/user/bucket", "fileId", "fileName", null);
    private Image imageWithOriginalData = new Image("domain.com/user/bucket", "fileId", "fileName", new Metadata(500, 500, "image/jpeg"));

    @Test
    public void fitWithAllOptions() throws Exception {
        String url = image.fit(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fit/w_100,h_100,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName"));
    }

    @Test
    public void fitWithAllOptionsAndOriginalData() throws Exception {
        String url = imageWithOriginalData.fit(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fit/w_100,h_100,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName#w_500,h_500,mt_image/jpeg"));
    }

    @Test
    public void fillWithAllOptions() throws Exception {
        String url = image.fill(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().upscale().quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/w_100,h_100,al_fs,bl,blur_10,br_10,con_10,eye,hue_10,lg,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName"));
    }

    @Test
    public void fillWithAllOptionsWithOriginalData() throws Exception {
        String url = imageWithOriginalData.fill(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().upscale().quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/w_100,h_100,al_fs,bl,blur_10,br_10,con_10,eye,hue_10,lg,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName#w_500,h_500,mt_image/jpeg"));
    }

    @Test
    public void cropWithAllOptions() throws Exception {
        String url = image.crop(100, 100, 1, 1, 1).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/crop/w_100,h_100,x_1,y_1,scl_1.0,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName"));
    }

    @Test
    public void cropWithAllOptionsWithOriginalData() throws Exception {
        String url = imageWithOriginalData.crop(100, 100, 1, 1, 1).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/crop/w_100,h_100,x_1,y_1,scl_1.0,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName#w_500,h_500,mt_image/jpeg"));
    }

    @Test
    public void canvasWithAllOptions() throws Exception {
        String url = image.canvas(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().background("aabbcc").quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/canvas/w_100,h_100,al_fs,bl,blur_10,br_10,c_aabbcc,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName"));
    }

    @Test
    public void canvasWithAllOptionsWithOriginalData() throws Exception {
        String url = imageWithOriginalData.canvas(100, 100).brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().background("aabbcc").quality(10).alignment(Alignment.ALL_FACES).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/canvas/w_100,h_100,al_fs,bl,blur_10,br_10,c_aabbcc,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName#w_500,h_500,mt_image/jpeg"));
    }

    @Test
    public void acceptsHTTP() throws Exception {
        String url = new Image("http://domain.com/user/bucket", "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("http://domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsHTTPS() throws Exception {
        String url = new Image("https://domain.com/user/bucket", "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("https://domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsDoubleSlash() throws Exception {
        String url = new Image("//domain.com/user/bucket", "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void defaultsToDoubleSlash() throws Exception {
        String url = new Image("domain.com/user/bucket", "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsNullBaseUrl() throws Exception {
        String url = new Image(null, "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsEmptyBaseUrl() throws Exception {
        String url = new Image("", "fileId", "fileName", null).fill(100, 100).toUrl();

        assertThat(url, is("/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void maintainsOptionsOrder() throws Exception {
        String url1 = image.fit(100, 100)
                .brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .baseline().quality(10).toUrl();
        String url2 = image.fit(100, 100)
                .negative().oil().pixelate(10).pixelateFaces(10).removeRedeye().sharpen(0.1f).unsharpMask(10, 10, 10)
                .brightness(10).contrast(10).hue(10).saturation(10).blur(10)
                .baseline().quality(10).toUrl();

        assertThat(url1, is(url2));
    }
}