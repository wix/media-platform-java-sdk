package com.wix.mediaplatform.v6.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.live.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LiveServiceTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private LiveService liveService = new LiveService(configuration, AuthenticatedHTTPClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getStream() throws Exception {
        stubFor(get(urlEqualTo("/_api/live/stream/wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-live-stream-response.json")));

        LiveStream liveStream = liveService.getStream("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM");

        assertThat(liveStream.getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }

    @Test
    public void listStreams() throws Exception {
        stubFor(get(urlEqualTo("/_api/live/list_streams"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-live-streams-response.json")));

        LiveStream[] liveStreams = liveService.listStreams();

        assertThat(liveStreams[0].getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }

    @Test
    public void createStream() throws Exception {
        stubFor(post(urlEqualTo("/_api/live/stream"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-live-stream-response.json")));

        OpenLiveStreamRequest openLiveStreamRequest = new OpenLiveStreamRequest()
                .setDigitalVideoRecorder(new DigitalVideoRecorder()
                    .setDestination(new Destination()
                                .setDirectory("/test/dvr")
                                .setAcl("public")))
                .setStreamType(LiveStream.Type.event)
                .setConnectTimeout(3600)
                .setGeo(new Geo()
                    .setCoordinates(new Geo.Coordinates()
                        .setLatitude(10.11f)
                        .setLongitude(10.11f)))
                .setMaxStreamingSec(3600)
                .setProtocol(LiveStream.Protocol.rtmp)
                .setStateNotification(new StateNotification()
                    .setCallbackUrl("http://www.example.com/callback")
                    .setCustomPayload(new HashMap<String,String>(){{
                        put("a", "b");
                        put("c", "d");
                    }}))
                .setReconnectTimeout(3600);

        LiveStream liveStream = liveService.openStream(openLiveStreamRequest);

        assertThat(liveStream.getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }
}
