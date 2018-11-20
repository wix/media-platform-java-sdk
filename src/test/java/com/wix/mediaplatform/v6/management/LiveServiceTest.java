package com.wix.mediaplatform.v6.management;

import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.live.*;
import org.junit.Test;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LiveServiceTest extends BaseTest {

    private LiveService liveService = new LiveService(configuration, authenticatedHttpClient);

    @Test
    public void getStream() throws Exception {
        stubFor(get(urlEqualTo("/_api/live/stream/wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-live-stream-response.json")));

        LiveStream liveStream = liveService.liveStreamRequest("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM")
                .execute();

        assertThat(liveStream.getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }

    @Test
    public void listStreams() throws Exception {
        stubFor(get(urlEqualTo("/_api/live/list_streams"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-live-streams-response.json")));

        LiveStream[] liveStreams = liveService.liveStreamListRequest().execute();

        assertThat(liveStreams[0].getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }

    @Test
    public void createStream() throws Exception {
        stubFor(post(urlEqualTo("/_api/live/stream"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("create-live-stream-response.json")));

        LiveStream liveStream = liveService.createLiveStreamRequest()
                .setDigitalVideoRecorder(new DigitalVideoRecorder()
                        .setDestination(new Destination()
                                .setDirectory("/test/dvr")
                                .setAcl(FileDescriptor.Acl.PRIVATE)))
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
                        .setCustomPayload(new HashMap<String, String>() {{
                            put("a", "b");
                            put("c", "d");
                        }}))
                .setReconnectTimeout(3600)
                .execute();

        assertThat(liveStream.getId(), is("wixmp-3dbd5cb16bb136182e099f78_R3PlOjNM"));
    }
}
