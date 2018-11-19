package com.wix.mediaplatform.v6.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.Source;
import com.wix.mediaplatform.v6.service.transcode.*;
import org.junit.Before;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TranscodeServiceTest extends BaseTest {

    private TranscodeService transcodeService = new TranscodeService(configuration, authenticatedHttpClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void transcodeFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/av/transcode"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("transcode-response.json")));

        TranscodeJobGroup response = transcodeService.transcodeRequest()
                .addSource(new Source().setPath("/test/video.mp4"))
                .addSpecification(new TranscodeSpecification()
                        .setDestination(new Destination()
                                .setDirectory("/test/encodes/")
                                .setAcl("public"))
                        .setQualityRange(new QualityRange()
                                .setMinimum("240p")
                                .setMaximum("1440p")))
                .execute();

        assertThat(response.getGroupId(), is("fb79405a16434aab87ccbd1384563033"));
        for (Job job : response.getJobs()) {
            assertThat(job instanceof TranscodeJob, is(true));
        }

    }

}