package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.*;
import com.wix.mediaplatform.dto.request.TranscodeRequest;
import com.wix.mediaplatform.dto.response.TranscodeJobsResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TranscodeManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);

    private TranscodeManager transcodeManager = new TranscodeManager(configuration, authenticatedHttpClient);

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

        TranscodeRequest transcodeRequest = new TranscodeRequest()
                .addSource(new Source().setPath("/test/video.mp4") )
                .addSpecification( new TranscodeSpecification()
                        .setDestination(new Destination()
                                .setDirectory("/test/encodes/")
                                .setAcl("public"))
                        .setQualityRange( new QualityRange()
                                .setMinimum("240p")
                                .setMaximum("1440p")));
        TranscodeJobsResponse response = transcodeManager.transcodeVideo(transcodeRequest);

        assertThat(response.getGroupId(), is("fb79405a16434aab87ccbd1384563033"));
        for (Job job: response.getJobs()) {
            assertThat(job instanceof TranscodeJob, is(true));
        }

    }

}