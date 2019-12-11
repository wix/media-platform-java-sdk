package com.wix.mediaplatform.v6.service.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.FileLifecycle;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @deprecated use UploadFileRequest
 */
@Deprecated
public class UploadFileRequestV2 extends UploadFileRequest {

    UploadFileRequestV2(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, ObjectMapper objectMapper) {
        super(authenticatedHTTPClient, baseUrl, objectMapper);
    }
}
