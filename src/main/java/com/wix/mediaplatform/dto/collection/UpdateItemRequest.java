package com.wix.mediaplatform.dto.collection;

import java.util.Map;
import java.util.Set;

public class UpdateItemRequest extends NewItemRequest {

    private String id;

    public UpdateItemRequest() {
    }

    public UpdateItemRequest(String type, String title, Set<String> tags, Map<String, String> publicProperties, Map<String, String> privateProperties, String id) {
        super(type, title, tags, publicProperties, privateProperties);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public UpdateItemRequest setId(String id) {
        this.id = id;
        return this;
    }
}
