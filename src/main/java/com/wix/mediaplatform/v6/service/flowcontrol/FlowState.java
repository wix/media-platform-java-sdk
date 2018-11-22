package com.wix.mediaplatform.v6.service.flowcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FlowState {

    private String id;

    private Status status;

    private Invocation invocation;

    private Map<String, Operation> operations;

    private FlowError error;

    public FlowState() {
    }

    public String getId() {
        return id;
    }

    public FlowState setId(String id) {
        this.id = id;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public FlowState setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Invocation getInvocation() {
        return invocation;
    }

    public FlowState setInvocation(Invocation invocation) {
        this.invocation = invocation;
        return this;
    }

    public Map<String, Operation> getOperations() {
        return operations;
    }

    public FlowState setOperations(Map<String, Operation> operations) {
        this.operations = operations;
        return this;
    }

    public FlowError getError() {
        return error;
    }

    public FlowState setError(FlowError error) {
        this.error = error;
        return this;
    }

    public enum Status {

        @JsonProperty("idle")
        idle("idle"),
        @JsonProperty("working")
        working("working"),
        @JsonProperty("success")
        success("success"),
        @JsonProperty("aborted")
        aborted("aborted"),
        @JsonProperty("error")
        error("error");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromString(String typeString) {
            return Status.valueOf(typeString);
        }
    }
}
