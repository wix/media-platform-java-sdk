package com.wix.mediaplatform.management;

import com.google.gson.Gson;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.BaseDTO;
import com.wix.mediaplatform.dto.management.ListFilesRequest;
import com.wix.mediaplatform.dto.management.ListFilesResponse;
import com.wix.mediaplatform.dto.management.UpdateFileRequest;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class FileManager {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final Gson gson;
    private final String baseUrl;

    public FileManager(AuthenticatedHTTPClient authenticatedHTTPClient, Gson gson, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.gson = gson;

        this.baseUrl = "https://" + configuration.getDomain();
    }

    public ListFilesResponse listFiles(String userId, @Nullable ListFilesRequest listFilesRequest) throws UnauthorizedException, IOException, URISyntaxException {

        Map<String, String> params = null;
        if (listFilesRequest != null) {
                params = listFilesRequest.toParams();
        }

        return authenticatedHTTPClient.get(userId, baseUrl + "/files/getpage", params, ListFilesResponse.class);
    }

    public BaseDTO getFile(String userId, String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        //TODO: resolve response to concrete Class
        return authenticatedHTTPClient.get(userId, baseUrl + "/files/" + fileId, null, BaseDTO.class);
    }

    public BaseDTO updateFile(String userId, String fileId, UpdateFileRequest updateFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        //TODO: resolve response to concrete Class
        return authenticatedHTTPClient.put(userId, baseUrl + "/files/" + fileId, updateFileRequest, null, BaseDTO.class);
    }

    public void deleteFile(String userId, String fileId) {

    }

}
