package com.wix.mediaplatform.v8.service.file;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.FileDescriptor;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

import java.util.Map;

// todo: pagination (.next())

public class FileListRequest extends MediaPlatformRequest<FileList> {

    private String path;

    private String nextPageToken;

    private Integer pageSize;

    private OrderBy orderBy;

    private OrderDirection orderDirection;

    private FileDescriptor.Type type;

    private Boolean recursive = false;

    public FileListRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files/ls_dir", FileList.class);
    }

    @Override
    protected Map<String, String> params() {

        ImmutableMap.Builder<String, String> b = ImmutableMap.builder();

        b.put("path", path);

        if (nextPageToken != null) {
            b.put("nextPageToken", nextPageToken);
        }
        if (pageSize != null ) {
            b.put("pageSize", pageSize.toString());
        }
        if (orderBy != null) {
            b.put("orderBy", orderBy.name());
        }
        if (orderDirection != null) {
            b.put("orderDirection", orderDirection.name());
        }
        if (recursive) {
            b.put("r", "yes");
        }
        if (type != null) {
            b.put("type", type.getValue());
        }

        return b.build();
    }

    public String getPath() {
        return path;
    }

    public FileListRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public FileListRequest setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public FileListRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public FileListRequest setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public FileListRequest setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
        return this;
    }

    public FileDescriptor.Type getType() {
        return type;
    }

    public FileListRequest setType(FileDescriptor.Type type) {
        this.type = type;
        return this;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public FileListRequest setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public enum OrderBy {
        name,
        dateUpdated
    }

    public enum OrderDirection {
        acs,
        des
    }
}
