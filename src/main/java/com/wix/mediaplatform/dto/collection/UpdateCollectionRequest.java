package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class UpdateCollectionRequest {

    private String title;

    private Set<String> tags = newHashSet();

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("public_properties")
    private Map<String, String> publicProperties = newHashMap();

    @SerializedName("private_properties")
    private Map<String, String> privateProperties = newHashMap();

    public UpdateCollectionRequest() {
    }

    public UpdateCollectionRequest(String title, Set<String> tags, String thumbnailUrl, Map<String, String> publicProperties, Map<String, String> privateProperties) {
        this.title = title;
        this.tags = tags;
        this.thumbnailUrl = thumbnailUrl;
        this.publicProperties = publicProperties;
        this.privateProperties = privateProperties;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Map<String, String> getPublicProperties() {
        return publicProperties;
    }

    public Map<String, String> getPrivateProperties() {
        return privateProperties;
    }

    public UpdateCollectionRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateCollectionRequest setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public UpdateCollectionRequest addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    public UpdateCollectionRequest setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    public UpdateCollectionRequest setPublicProperties(Map<String, String> publicProperties) {
        this.publicProperties = publicProperties;
        return this;
    }

    public UpdateCollectionRequest putPublicProperty(String key, String value) {
        this.publicProperties.put(key, value);
        return this;
    }

    public UpdateCollectionRequest setPrivateProperties(Map<String, String> privateProperties) {
        this.privateProperties = privateProperties;
        return this;
    }

    public UpdateCollectionRequest putPrivateProperty(String key, String value) {
        this.privateProperties.put(key, value);
        return this;
    }
}
