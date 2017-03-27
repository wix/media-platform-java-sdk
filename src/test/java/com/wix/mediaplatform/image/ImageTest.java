package com.wix.mediaplatform.image;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ImageTest {

    @Test
    public void crop() throws Exception {
        String url = new Image("//test.com/file.png/v1/crop/w_709,h_400,x_1,y_2,scl_1,q_75,usm_0.5_0.2_0.0/file.png")
                .crop(100, 200, 1, 2, 3)
                .brightness(10)
                .contrast(10)
                .hue(10)
                .saturation(10)
                .blur(10)
                .unsharpMask(10, 10, 10)
                .jpeg(10)
                .toUrl();

        assertThat(url, is("//test.com/file.png/v1/crop/w_100,h_200,x_1,y_2,scl_3.0,br_10,q_10,con_10,sat_10,hue_10,blur_10,usm_10.00_10.00_10.00/file.png"));
    }

    @Test
    public void cropWithMetadata() throws Exception {
        String url = new Image("//test.com/images/file.png/v1/crop/w_709,h_400,x_1,y_2,scl_1,q_75,usm_0.5_0.2_0.0/file.png#w_1000,h_2000,mt_image%2Fpng")
                .crop(100, 200, 1, 2, 3)
                .brightness(10)
                .contrast(10)
                .hue(10)
                .saturation(10)
                .blur(10)
                .unsharpMask(10, 10, 10)
                .jpeg(10)
                .toUrl();

        assertThat(url, is("//test.com/images/file.png/v1/crop/w_100,h_200,x_1,y_2,scl_3.0,br_10,q_10,con_10,sat_10,hue_10,blur_10,usm_10.00_10.00_10.00/file.png#w_1000,h_2000,mt_image/png"));
    }

    @Test
    public void acceptsHTTP() throws Exception {
        String url = new Image("http://test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1/file.png#w_1000,h_2000,mt_image%2Fpng").toUrl();

        assertThat(url, is("http://test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1.0/file.png#w_1000,h_2000,mt_image/png"));
    }

    @Test
    public void acceptsHTTPS() throws Exception {
        String url = new Image("https://test.com/dog.jpeg/v1/crop/w_709,h_400,x_1,y_2,scl_1/dog.jpeg#w_1000,h_2000,mt_image%2Fjpeg").toUrl();

        assertThat(url, is("https://test.com/dog.jpeg/v1/crop/w_709,h_400,x_1,y_2,scl_1.0/dog.jpeg#w_1000,h_2000,mt_image/jpeg"));
    }

    @Test
    public void acceptsDoubleSlash() throws Exception {
        String url = new Image("//images-wixmp-8cbe8e680e95a22c77c8d3d0.wixmp.com/media_manager_demo/common/SanFranHouse.jpg/v1/crop/w_475,h_267,x_1,y_2,scl_1.0/SanFranHouse.jpg").toUrl();

        assertThat(url, is("//images-wixmp-8cbe8e680e95a22c77c8d3d0.wixmp.com/media_manager_demo/common/SanFranHouse.jpg/v1/crop/w_475,h_267,x_1,y_2,scl_1.0/SanFranHouse.jpg"));
    }
}