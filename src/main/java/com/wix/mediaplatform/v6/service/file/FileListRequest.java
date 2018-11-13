package com.wix.mediaplatform.v6.service.file;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.v6.service.FileDescriptor;

import java.util.Map;

public class FileListRequest {

    private String nextPageToken;

    private Integer pageSize;

    private OrderBy orderBy;

    private OrderDirection orderDirection;

    private FileDescriptor.Type type;

    private Boolean recursive = false;

    public FileListRequest() {

    }

    public FileListRequest(String nextPageToken, Integer pageSize, OrderBy orderBy, OrderDirection orderDirection) {
        this.nextPageToken = nextPageToken;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public FileListRequest(String nextPageToken, Integer pageSize, OrderBy orderBy, OrderDirection orderDirection,
                           FileDescriptor.Type type, Boolean recursive) {
        this.nextPageToken = nextPageToken;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.type = type;
        this.recursive = recursive;
    }

    public FileListRequest setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public FileListRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public FileListRequest setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public FileListRequest ascending() {
        this.orderDirection = OrderDirection.acs;
        return this;
    }

    public FileListRequest descending() {
        this.orderDirection = OrderDirection.des;
        return this;
    }

    public FileListRequest setType(FileDescriptor.Type type) {
        this.type = type;
        return this;
    }

    public FileListRequest setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public int getPageSize() {
        return pageSize;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public FileDescriptor.Type getType() {
        return type;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public Map<String, String> toParams() {

        ImmutableMap.Builder<String, String> b = ImmutableMap.builder();

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

    public enum OrderBy {
        name,
        dateUpdated
    }

    public enum OrderDirection {
        acs,
        des
    }
}
