package com.wix.mediaplatform.v6.management;

import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.RestResponse;
import com.wix.mediaplatform.v6.service.request.TranscodeRequest;
import com.wix.mediaplatform.v6.service.response.TranscodeJobsResponse;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wix.mediaplatform.v6.gson.Types.TRANSCODE_JOBS_REST_RESPONSE;

public class TranscodeManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;
    private final String baseUrl;

    public TranscodeManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = configuration.getBaseUrl() + "/_api";
    }

    public TranscodeJobsResponse transcodeVideo(TranscodeRequest transcodeRequest) throws MediaPlatformException,
            IOException, URISyntaxException {
        RestResponse<TranscodeJobsResponse> restResponse = authenticatedHttpClient.post(
                baseUrl + "/av/transcode",
                transcodeRequest,
                null,
                TRANSCODE_JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
