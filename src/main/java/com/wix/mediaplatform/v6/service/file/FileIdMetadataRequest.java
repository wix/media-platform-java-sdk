package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class FileIdMetadataRequest extends MediaPlatformRequest<FileDescriptor> {

    FileIdMetadataRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String fileId) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files/" + fileId + "/metadata");
    }

//    @Nullable
//    public FileMetadata getFileMetadataByPath(String path) throws MediaPlatformException, IOException, URISyntaxException {
//        Map<String, String> params = newHashMap();
//        params.put("path", path);
//
//        RestResponse<FileMetadata> restResponse = authenticatedHttpClient.get(
//                baseUrl + "/files/metadata",
//                params,
//                Types.FILE_METADATA_REST_RESPONSE);
//        return restResponse.getPayload();
//    }
}
