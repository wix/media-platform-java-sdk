package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.FileDescriptor;
import com.wix.mediaplatform.dto.management.ListFilesRequest;
import com.wix.mediaplatform.dto.management.ListFilesResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class FileManager {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final String baseUrl;

    public FileManager(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.baseUrl = "https://" + configuration.getDomain();
    }

    public ListFilesResponse listFiles(@Nullable ListFilesRequest listFilesRequest) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = null;
        if (listFilesRequest != null) {
                params = listFilesRequest.toParams();
        }

        return authenticatedHTTPClient.get(baseUrl + "/files/getpage", params, ListFilesResponse.class);
    }

    @Nullable
    public FileDescriptor getFile(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.get(baseUrl + "/files/" + fileId, null, FileDescriptor.class);
    }

    public FileDescriptor updateFile(String fileId, UpdateFileRequest updateFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.put(baseUrl + "/files/" + fileId, updateFileRequest, null, FileDescriptor.class);
    }

    public void deleteFile(String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        authenticatedHTTPClient.delete(baseUrl + "/files/" + fileId, null, null);
    }
}
