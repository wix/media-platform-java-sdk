package com.wix.mediaplatform.filedownloader;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.download.GetSecureUrlRequest;
import com.wix.mediaplatform.dto.download.GetSecureUrlResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileDownloader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String baseUrl;

    public FileDownloader(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.baseUrl = "https://" + configuration.getDomain();
    }

    //TODO: generate the secure URL locally
    public GetSecureUrlResponse[] getSecureUrls(String userId, GetSecureUrlRequest getSecureUrlRequest) throws IOException, UnauthorizedException {
        Map<String, Object> additionalClaims = newHashMap();
        additionalClaims.put("encoding", StringUtils.join(getSecureUrlRequest.getEncoding(), ","));
        if (getSecureUrlRequest.getExpiresIn() != null) {
            additionalClaims.put("expiry", getSecureUrlRequest.getExpiresIn());
        }
        if (getSecureUrlRequest.getSaveAs() != null) {
            additionalClaims.put("save_as", getSecureUrlRequest.getSaveAs());
        }
        if (getSecureUrlRequest.getExpiredRedirectUrl() != null) {
            additionalClaims.put("expiration_redirect_url", getSecureUrlRequest.getExpiredRedirectUrl());
        }

        return authenticatedHTTPClient.postWithSelfSignedToken(userId, baseUrl + "/secure-files/" + getSecureUrlRequest.getFileId() + "/tickets/create", null, additionalClaims, GetSecureUrlResponse[].class);
    }

    public GetSecureUrlResponse[] getSecureUrls(String userId, GetSecureUrlRequest[] getSecureUrlRequests) throws IOException, UnauthorizedException {
        ArrayList<GetSecureUrlResponse> responses = new ArrayList<>();
        for (GetSecureUrlRequest request : getSecureUrlRequests) {
            Collections.addAll(responses, getSecureUrls(userId, request));
        }

        return responses.toArray(new GetSecureUrlResponse[responses.size()]);
    }
}
