package com.wix.mediaplatform.v7.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileLifecycle {

    private Integer age;

    private Action action;

    public FileLifecycle() {}

    public String getAction() {
        return action.getValue();
    }

    public FileLifecycle setAction(Action action) {
        this.action = action;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public FileLifecycle setAge(Integer age) {
        this.age = age;
        return this;
    }

    public enum Action {

        @JsonProperty("delete")
        DELETE("delete");

        private final String action;

        Action(String action) {
            this.action = action;
        }

        public String getValue() {
            return action;
        }

        public static Action fromString(String asString) {
            for (FileLifecycle.Action type: FileLifecycle.Action .values()) {
                if (type.getValue().equals(asString)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid value for job type: " + asString);
        }
    }
}
