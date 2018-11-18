package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class DeleteFileIdRequest extends MediaPlatformRequest {

    DeleteFileIdRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String fileId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/files/" + fileId);
    }

}
