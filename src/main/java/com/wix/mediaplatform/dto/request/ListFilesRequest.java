package com.wix.mediaplatform.dto.request;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ListFilesRequest {

    private String nextPageToken;

    private Integer pageSize;

    private OrderBy orderBy;

    private OrderDirection orderDirection;

    public ListFilesRequest() {

    }

    public ListFilesRequest(String nextPageToken, Integer pageSize, OrderBy orderBy, OrderDirection orderDirection) {
        this.nextPageToken = nextPageToken;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public ListFilesRequest setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public ListFilesRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ListFilesRequest setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public ListFilesRequest ascending() {
        this.orderDirection = OrderDirection.acs;
        return this;
    }

    public ListFilesRequest descending() {
        this.orderDirection = OrderDirection.des;
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
