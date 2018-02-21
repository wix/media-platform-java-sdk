package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.CreateArchiveJob;
import com.wix.mediaplatform.dto.job.ExtractArchiveJob;
import com.wix.mediaplatform.dto.request.CreateArchiveRequest;
import com.wix.mediaplatform.dto.request.ExtractArchiveRequest;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wix.mediaplatform.gson.Types.JOB_REST_RESPONSE;

public class ArchiveManager {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String baseUrl;

    public ArchiveManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHTTPClient) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.baseUrl = configuration.getBaseUrl() + "/_api";
    }

    public CreateArchiveJob createArchive(CreateArchiveRequest createArchiveRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<CreateArchiveJob> restResponse = authenticatedHTTPClient.post(
                baseUrl + "/archive/create",
                createArchiveRequest,
                null,
                JOB_REST_RESPONSE);

        return restResponse.getPayload();
    }

    public ExtractArchiveJob extractArchive(ExtractArchiveRequest extractArchiveRequest) throws MediaPlatformException, IOException, URISyntaxException {
        RestResponse<ExtractArchiveJob> restResponse = authenticatedHTTPClient.post(
                baseUrl + "/archive/extract",
                extractArchiveRequest,
                null,
                JOB_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
