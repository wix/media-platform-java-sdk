package com.wix.mediaplatform.v8.service.flowcontrol;

public class FlowError {

    private String componentType;

    private String componentKey;

    private String message;

    public FlowError() {
    }

    public String getComponentType() {
        return componentType;
    }

    public FlowError setComponentType(String componentType) {
        this.componentType = componentType;
        return this;
    }

    public String getComponentKey() {
        return componentKey;
    }

    public FlowError setComponentKey(String componentKey) {
        this.componentKey = componentKey;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FlowError setMessage(String message) {
        this.message = message;
        return this;
    }
}
