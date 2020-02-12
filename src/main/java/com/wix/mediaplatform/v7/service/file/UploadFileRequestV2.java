package com.wix.mediaplatform.v7.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;

/**
 * @deprecated use UploadFileRequest
 */
@Deprecated
public class UploadFileRequestV2 extends UploadFileRequest {

    UploadFileRequestV2(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        super(authenticatedHTTPClient, baseUrl, objectMapper);
    }
}
