package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.request.TranscodeRequest;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.dto.response.TranscodeJobsResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wix.mediaplatform.gson.Types.TRANSCODE_JOBS_REST_RESPONSE;

public class TranscodeManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;
    private final String baseUrl;

    public TranscodeManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = "https://" + configuration.getDomain() + "/_api";
    }

    public TranscodeJobsResponse transcodeVideo(TranscodeRequest transcodeRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<TranscodeJobsResponse> restResponse = authenticatedHttpClient.post(
                baseUrl + "/av/transcode",
                transcodeRequest,
                null,
                TRANSCODE_JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
