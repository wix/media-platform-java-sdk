package com.wix.mediaplatform.v6.service.image;

import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.metadata.features.ImageFeatures;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.Source;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ImageServiceTest extends BaseTest {

    private ImageService imageService = new ImageService(configuration, authenticatedHttpClient);

    @Test
    public void extractFeatures() throws Exception {
        stubFor(get(urlEqualTo("/_api/images/features?features=faces%2Clabels%2Cexplicit_content&path=%2Fcat.png"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-features-response.json")));

        ImageFeatures imageFeatures = imageService.extractFeaturesRequest()
                .setFeatures(new String[]{"faces", "labels", "explicit_content"})
                .setPath("/cat.png")
                .execute();

        assertThat(imageFeatures.getFaces()[0].getX(), is(383));
    }

    @Test
    public void imageOperation() throws Exception {
        stubFor(post(urlEqualTo("/_api/images/operations"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-operation-response.json")));

        FileDescriptor fileDescriptor = imageService.imageOperationRequest()
                .setSource(new Source().setPath("/cat.jpg"))
                .setSpecification(new ImageOperationSpecification()
                        .setCommand("/v1/fill/w_100,h_100")
                        .setDestination(new Destination().setPath("/small-cat.jpg")))
                .execute();

        assertThat(fileDescriptor.getPath(), is("/small-cat.jpg"));
    }
}