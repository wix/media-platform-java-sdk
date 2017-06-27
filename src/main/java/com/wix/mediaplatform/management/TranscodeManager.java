package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.job.TranscodeJobResult;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.request.*;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.ListFilesResponse;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.dto.response.TranscodeJobsResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.gson.Types.*;

public class TranscodeManager {

    private final AuthenticatedHTTPClient authenticatedHttpClient;
    private final String baseUrl;

    public TranscodeManager(Configuration configuration, AuthenticatedHTTPClient authenticatedHttpClient) {
        this.authenticatedHttpClient = authenticatedHttpClient;

        this.baseUrl = "https://" + configuration.getDomain() + "/_api";
    }

    public TranscodeJobsResponse transcodeVideo(TranscodeRequest transcodeRequest) throws UnauthorizedException, IOException, URISyntaxException {
        RestResponse<TranscodeJobsResponse> restResponse = authenticatedHttpClient.post(
                baseUrl + "/av/transcode",
                transcodeRequest,
                null,
                TRANSCODE_JOBS_REST_RESPONSE);

        return restResponse.getPayload();
    }
}
