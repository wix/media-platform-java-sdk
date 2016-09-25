package com.wix.mediaplatform.filedownloader;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.download.GetSecureUrlRequest;
import com.wix.mediaplatform.dto.download.GetSecureUrlResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileDownloader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;

    private final String baseUrl;

    public FileDownloader(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.baseUrl = "https://" + configuration.getDomain();
    }

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

        return authenticatedHTTPClient.post(userId, baseUrl + "/secure-files/" + getSecureUrlRequest.getFileId() + "/tickets/create", additionalClaims, GetSecureUrlResponse[].class);
    }
}
