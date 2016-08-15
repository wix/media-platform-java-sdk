package com.wix.mediaplatform.image;

import com.google.common.collect.Sets;
import com.wix.mediaplatform.dto.image.ImageDTO;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParserTest {

    @Test
    public void fromDto() throws Exception {
        ImageRequest imageRequest = Parser.fromDto("test.wix.com", new ImageDTO("parentFolderId", "hash", "fish.jpeg",
                "imageId", "user/bucket/fileId/file.jpg", 1000,
                "iconUrl", "picture", "image/jpeg", Sets.<String>newHashSet(), Sets.<String>newHashSet(), 100, 100,
                10, 10, Sets.<ImageDTO.Square>newHashSet()));

        assertThat(imageRequest.fit(100, 100).toUrl(), is("//test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg"));
    }

    @Test
    public void fromUrl() throws Exception {
        ImageRequest imageRequest = Parser.fromUrl("http://test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg");

        assertThat(imageRequest.fit(100, 100).toUrl(), is("http://test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg"));
    }

    @Test
    public void fromUrlWithPort() throws Exception {
        ImageRequest imageRequest = Parser.fromUrl("http://test.wix.com:8080/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg");

        assertThat(imageRequest.fit(100, 100).toUrl(), is("http://test.wix.com:8080/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg"));
    }

    @Test
    public void fromUrlEncodedFragment() throws Exception {
        ImageRequest imageRequest = Parser.fromUrl("http://test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image%2Fjpeg");

        assertThat(imageRequest.fit(100, 100).toUrl(), is("http://test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg"));
    }

    @Test
    public void fromUrlWithoutProtocol() throws Exception {
        ImageRequest imageRequest = Parser.fromUrl("//test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg");

        assertThat(imageRequest.fit(100, 100).toUrl(), is("//test.wix.com/user/bucket/imageId/v1/fit/w_100,h_100/fish.jpeg#w_10,h_10,mt_image/jpeg"));
    }

    @Test
    public void operationFromUrl() throws Exception {

    }
}