package com.wix.mediaplatform.dto.collection;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class NewCollectionRequest extends UpdateCollectionRequest {

    private String type;

    private Set<NewItemRequest> items = newHashSet();

    public NewCollectionRequest() {
    }

    public NewCollectionRequest(String title, Set<String> tags, String thumbnailUrl, Map<String, String> publicProperties, Map<String, String> privateProperties, String type, Set<NewItemRequest> items) {
        super(title, tags, thumbnailUrl, publicProperties, privateProperties);
        this.type = type;
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public Set<NewItemRequest> getItems() {
        return items;
    }

    public NewCollectionRequest setType(String type) {
        this.type = type;
        return this;
    }

    public NewCollectionRequest setItems(Set<NewItemRequest> items) {
        this.items = items;
        return this;
    }

    public NewCollectionRequest addItem(NewItemRequest item) {
        this.items.add(item);
        return this;
    }
}
