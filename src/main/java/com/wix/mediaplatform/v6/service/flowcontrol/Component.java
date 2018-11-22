package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.service.Specification;

public class Component {

    private String type;

    private Specification specification;

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

    public Specification getSpecification() {
        return specification;
    }

    public Component setSpecification(Specification specification) {
        this.specification = specification;
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
}
