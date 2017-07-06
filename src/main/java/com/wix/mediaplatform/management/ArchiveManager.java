package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.request.ExtractArchiveRequest;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wix.mediaplatform.gson.Types.JOB_REST_RESPONSE;

public class ArchiveManager {

    private final Configuration configuration;

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String baseUrl;

    public ArchiveManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        this.configuration = configuration;
        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.baseUrl = "https://" + configuration.getDomain() + "/_api";
    }

    public Job extractArchive(ExtractArchiveRequest extractArchiveRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<Job> restResponse = authenticatedHTTPClient.post(
                baseUrl + "/archive/extract",
                extractArchiveRequest,
                null,
                JOB_REST_RESPONSE);
        restResponse.throwForErrorCode();

        return restResponse.getPayload();
    }
}
