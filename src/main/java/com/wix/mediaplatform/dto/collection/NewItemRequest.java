package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class NewItemRequest {

    private String type;

    private String title;

    private Set<String> tags = newHashSet();

    @SerializedName("public_properties")
    private Map<String, String> publicProperties = newHashMap();

    @SerializedName("private_properties")
    private Map<String, String> privateProperties = newHashMap();

    public NewItemRequest() {
    }

    public NewItemRequest(String type, String title, Set<String> tags, Map<String, String> publicProperties, Map<String, String> privateProperties) {
        this.type = type;
        this.title = title;
        this.tags = tags;
        this.publicProperties = publicProperties;
        this.privateProperties = privateProperties;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Map<String, String> getPublicProperties() {
        return publicProperties;
    }

    public Map<String, String> getPrivateProperties() {
        return privateProperties;
    }

    public NewItemRequest setType(String type) {
        this.type = type;
        return this;
    }

    public NewItemRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public NewItemRequest setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public NewItemRequest setPublicProperties(Map<String, String> publicProperties) {
        this.publicProperties = publicProperties;
        return this;
    }

    public NewItemRequest addPublicProperty(String key, String value) {
        this.publicProperties.put(key, value);
        return this;
    }

    public NewItemRequest setPrivateProperties(Map<String, String> privateProperties) {
        this.privateProperties = privateProperties;
        return this;
    }

    public NewItemRequest addPrivateProperty(String key, String value) {
        this.privateProperties.put(key, value);
        return this;
    }
}
