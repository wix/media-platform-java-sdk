package com.wix.mediaplatform.v6.service.job;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

// todo: pagination (.next())

public class SearchJobsRequest extends MediaPlatformRequest<SearchJobsResponse> {

    private String nextPageToken;

    private Integer pageSize;

    private OrderBy orderBy;

    private OrderDirection orderDirection;

    private String issuer;

    private String type;

    private String status;

    private String groupId;

    private String fileId;

    private String path;

    public SearchJobsRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "jobs");
    }

    public SearchJobsRequest setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public SearchJobsRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public SearchJobsRequest setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public SearchJobsRequest ascending() {
        this.orderDirection = OrderDirection.acs;
        return this;
    }

    public SearchJobsRequest descending() {
        this.orderDirection = OrderDirection.des;
        return this;
    }

    public SearchJobsRequest setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
        return this;
    }

    public SearchJobsRequest setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public SearchJobsRequest setType(String type) {
        this.type = type;
        return this;
    }

    public SearchJobsRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public SearchJobsRequest setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public SearchJobsRequest setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public SearchJobsRequest setPath(String path) {
        this.path = path;
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

    public Map<String, String> params() {

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
        if (issuer != null) {
            b.put("issuer", issuer);
        }
        if (type != null) {
            b.put("type", type);
        }
        if (status != null) {
            b.put("status", status);
        }
        if (groupId != null) {
            b.put("groupId", groupId);
        }
        if (fileId != null) {
            b.put("fileId", fileId);
        }
        if (path != null) {
            b.put("path", path);
        }
        return b.build();
    }

    public enum OrderBy {
        name,
        date
    }

    public enum OrderDirection {
        acs,
        des
    }
}
