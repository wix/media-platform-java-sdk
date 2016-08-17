package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.FileBaseDTO;
import com.wix.mediaplatform.dto.folder.FolderDTO;
import com.wix.mediaplatform.dto.management.*;
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

    public ListFilesResponse listFiles(String userId, @Nullable ListFilesRequest listFilesRequest) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> params = null;
        if (listFilesRequest != null) {
                params = listFilesRequest.toParams();
        }

        return authenticatedHTTPClient.get(userId, baseUrl + "/files/getpage", params, ListFilesResponse.class);
    }

    public FileBaseDTO getFile(String userId, String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        //TODO: resolve response to concrete Class
        return authenticatedHTTPClient.get(userId, baseUrl + "/files/" + fileId, null, FileBaseDTO.class);
    }

    public FileBaseDTO updateFile(String userId, String fileId, UpdateFileRequest updateFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        //TODO: resolve response to concrete Class
        return authenticatedHTTPClient.put(userId, baseUrl + "/files/" + fileId, updateFileRequest, null, FileBaseDTO.class);
    }

    public void deleteFile(String userId, String fileId) throws UnauthorizedException, IOException, URISyntaxException {
        authenticatedHTTPClient.delete(userId, baseUrl + "/files/" + fileId, null, null);
    }

    public ListFoldersResponse listFolders(String userId, @Nullable String parentFolderId) throws UnauthorizedException, IOException, URISyntaxException {
        String url = baseUrl + "/folders" + (parentFolderId != null ? "/" + parentFolderId : "");
        return authenticatedHTTPClient.get(userId, url, null, ListFilesResponse.class);
    }

    public FolderDTO newFolder(String userId, NewFolderRequest newFolderRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/folders", newFolderRequest, null, FolderDTO.class);
    }

    public FolderDTO updateFolder(String userId, String folderId, UpdateFolderRequest updateFolderRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.put(userId, baseUrl + "/folders/" + folderId, updateFolderRequest, null, FolderDTO.class);
    }

    public void deleteFolder(String userId, String folderId) throws UnauthorizedException, IOException, URISyntaxException {
        authenticatedHTTPClient.delete(userId, baseUrl + "/folders/" + folderId, null, null);
    }
}
