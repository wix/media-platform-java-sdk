package com.wix.mediaplatform.dto.management;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.MediaType;

import java.util.Map;

public class ListFilesRequest {

    private String cursor;

    @SerializedName("page_size")
    private Integer size;

    private String order;

    private String orderDirection;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    @SerializedName("media_type")
    private MediaType mediaType;

    private String tag;

    public ListFilesRequest(String cursor, Integer size, String order, String orderDirection, String parentFolderId, MediaType mediaType, String tag) {
        this.cursor = cursor;
        this.size = size;
        this.order = order;
        this.orderDirection = orderDirection;
        this.parentFolderId = parentFolderId;
        this.mediaType = mediaType;
        this.tag = tag;
    }

    public String getCursor() {
        return cursor;
    }

    public int getSize() {
        return size;
    }

    public String getOrder() {
        return order;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getTag() {
        return tag;
    }

    public Map<String, String> toParams() {

        ImmutableMap.Builder<String, String> b = ImmutableMap.builder();

        if (cursor != null) {
            b.put("cursor", cursor);
        }
        if (size != null ) {
            b.put("page_size", size.toString());
        }
        if (order != null) {
            b.put("order", (orderDirection != null ? orderDirection : "") + order);
        }
        if (parentFolderId != null) {
            b.put("parentFolderId", parentFolderId);
        }
        if (mediaType != null) {
            b.put("media_type", mediaType.getMediaType());
        }
        if (tag != null) {
            b.put("tag", tag);
        }

        return b.build();
    }
}
