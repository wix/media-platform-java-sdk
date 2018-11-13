package com.wix.mediaplatform.v6.management;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.dto.live.LiveStream;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.gson.Types;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.RestResponse;
import com.wix.mediaplatform.v6.service.request.LiveStreamRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public class LiveManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;

    private final String baseUrl;

    public LiveManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = configuration.getBaseUrl() + "/_api/live";
    }

    public LiveStream openStream(LiveStreamRequest liveStreamRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<LiveStream> restResponse = authenticatedHttpClient.post(
                baseUrl + "/stream",
                liveStreamRequest,
                null,
                Types.LIVE_STREAM_RESPONSE);

        return restResponse.getPayload();
    }

    public LiveStream getStream(String streamId) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<LiveStream> restResponse = authenticatedHttpClient.get(
                baseUrl + "/stream/" + streamId,
                null,
                Types.LIVE_STREAM_RESPONSE);

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
                Types.LIVE_STREAM_LIST_RESPONSE
        );
        return restResponse.getPayload();
    }
}
