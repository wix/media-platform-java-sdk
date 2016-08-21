package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class ItemDTO {

    private String id;

    private String type;

    private String title;

    @SerializedName("sort_order")
    private float ordinal;

    private Set<String> tags = newHashSet();

    @SerializedName("public_properties")
    private Map<String, String> publicProperties = newHashMap();

    @SerializedName("private_properties")
    private Map<String, String> privateProperties = newHashMap();

    @SerializedName("date_created")
    private String dateCreated;

    @SerializedName("date_updated")
    private String dateUpdated;

    public ItemDTO() {
    }

    public ItemDTO(String id, String type, String title, float ordinal, Set<String> tags, Map<String, String> publicProperties, Map<String, String> privateProperties, String dateCreated, String dateUpdated) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.ordinal = ordinal;
        this.tags = tags;
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

    public float getOrdinal() {
        return ordinal;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", ordinal=" + ordinal +
                ", tags=" + tags +
                ", publicProperties=" + publicProperties +
                ", privateProperties=" + privateProperties +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                '}';
    }
}
