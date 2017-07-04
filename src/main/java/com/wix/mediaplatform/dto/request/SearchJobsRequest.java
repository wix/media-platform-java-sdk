package com.wix.mediaplatform.dto.request;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class SearchJobsRequest {

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

    public SearchJobsRequest() {}

    public SearchJobsRequest(String nextPageToken, Integer pageSize, OrderBy orderBy, OrderDirection orderDirection) {
        this.nextPageToken = nextPageToken;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchJobsRequest that = (SearchJobsRequest) o;

        if (nextPageToken != null ? !nextPageToken.equals(that.nextPageToken) : that.nextPageToken != null)
            return false;
        if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) return false;
        if (orderBy != that.orderBy) return false;
        if (orderDirection != that.orderDirection) return false;
        if (issuer != null ? !issuer.equals(that.issuer) : that.issuer != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        return path != null ? path.equals(that.path) : that.path == null;
    }

    @Override
    public int hashCode() {
        int result = nextPageToken != null ? nextPageToken.hashCode() : 0;
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
        result = 31 * result + (orderDirection != null ? orderDirection.hashCode() : 0);
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchJobsRequest{" +
                "nextPageToken='" + nextPageToken + '\'' +
                ", pageSize=" + pageSize +
                ", orderBy=" + orderBy +
                ", orderDirection=" + orderDirection +
                ", issuer='" + issuer + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", groupId='" + groupId + '\'' +
                ", fileId='" + fileId + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
