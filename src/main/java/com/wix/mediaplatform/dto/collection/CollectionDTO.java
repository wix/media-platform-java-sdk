package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class CollectionDTO {

    private String id;

    private String type;

    private String title;

    private Set<String> tags = newHashSet();

    private Set<ItemDTO> items = newHashSet();

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("public_properties")
    private Map<String, String> publicProperties;

    @SerializedName("private_properties")
    private Map<String, String> privateProperties;

    @SerializedName("date_created")
    private String dateCreated;

    @SerializedName("date_updated")
    private String dateUpdated;

    public CollectionDTO() {
    }

    public CollectionDTO(String id, String type, String title, Set<String> tags, Set<ItemDTO> items, String thumbnailUrl, Map<String, String> publicProperties, Map<String, String> privateProperties, String dateCreated, String dateUpdated) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.tags = tags;
        this.items = items;
        this.thumbnailUrl = thumbnailUrl;
        this.publicProperties = publicProperties;
        this.privateProperties = privateProperties;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getId() {
        return id;
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

    public Set<ItemDTO> getItems() {
        return items;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return "CollectionDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", items=" + items +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", publicProperties=" + publicProperties +
                ", privateProperties=" + privateProperties +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                '}';
    }
}
