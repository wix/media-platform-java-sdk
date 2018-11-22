package com.wix.mediaplatform.v6.service.flowcontrol;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wix.mediaplatform.v6.service.Specification;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImportFileComponent.class, name = "file.import"),
        @JsonSubTypes.Type(value = TranscodeComponent.class, name = "av.transcode"),
        @JsonSubTypes.Type(value = CreateUrlSetComponent.class, name = "av.create_urlset"),
})
public abstract class Component {

    private String type;

    private String[] successors;

    private boolean deleteSources = false;

    public Component() {
    }

    public String getType() {
        return type;
    }

    public Component setType(String type) {
        this.type = type;
        return this;
    }

    public String[] getSuccessors() {
        return successors;
    }

    public Component setSuccessors(String[] successors) {
        this.successors = successors;
        return this;
    }

    public boolean isDeleteSources() {
        return deleteSources;
    }

    public Component setDeleteSources(boolean deleteSources) {
        this.deleteSources = deleteSources;
        return this;
    }

    public abstract Specification getSpecification();
}
