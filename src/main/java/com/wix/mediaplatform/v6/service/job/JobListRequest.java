package com.wix.mediaplatform.v6.service.job;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

// todo: pagination (.next())

public class JobListRequest extends MediaPlatformRequest<JobList> {

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

    public JobListRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "jobs", JobList.class);
    }

    public JobListRequest setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public JobListRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public JobListRequest setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public JobListRequest ascending() {
        this.orderDirection = OrderDirection.acs;
        return this;
    }

    public JobListRequest descending() {
        this.orderDirection = OrderDirection.des;
        return this;
    }

    public JobListRequest setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
        return this;
    }

    public JobListRequest setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public JobListRequest setType(String type) {
        this.type = type;
        return this;
    }

    public JobListRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public JobListRequest setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public JobListRequest setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public JobListRequest setPath(String path) {
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
