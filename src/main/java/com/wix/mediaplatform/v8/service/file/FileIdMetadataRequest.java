package com.wix.mediaplatform.v8.service.file;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.metadata.FileMetadata;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class FileIdMetadataRequest extends MediaPlatformRequest<FileMetadata> {

    FileIdMetadataRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String fileId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files/" + fileId + "/metadata",
                FileMetadata.class);
    }

}
