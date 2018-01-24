package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.live.LiveStream;
import com.wix.mediaplatform.dto.request.LiveStreamRequest;
import com.wix.mediaplatform.dto.response.LiveStreamsResponse;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wix.mediaplatform.gson.Types.LIVE_STREAM_LIST_RESPONSE;
import static com.wix.mediaplatform.gson.Types.LIVE_STREAM_RESPONSE;

public class LiveManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;
    private final String baseUrl;

    public LiveManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = "https://" + configuration.getDomain() + "/_api/live";
    }

    public LiveStream openStream(LiveStreamRequest liveStreamRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<LiveStream> restResponse = authenticatedHttpClient.post(
                baseUrl + "/stream",
                liveStreamRequest,
                null,
                LIVE_STREAM_RESPONSE);

        return restResponse.getPayload();
    }

    public LiveStream getStream(String streamId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<LiveStream> restResponse = authenticatedHttpClient.get(
                baseUrl + "/stream/" + streamId,
                null,
                LIVE_STREAM_RESPONSE);

        return restResponse.getPayload();
    }

    public void closeStream(String streamId) throws MediaPlatformException, IOException, URISyntaxException {
        authenticatedHttpClient.delete(
                baseUrl + "/stream/" + streamId,
                null,
                null);
    }


    public LiveStream[] listStreams() throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<LiveStream[]> restResponse = authenticatedHttpClient.get(
                baseUrl + "/list_streams",
                null,
                LIVE_STREAM_LIST_RESPONSE
        );
        return restResponse.getPayload();
    }
}
