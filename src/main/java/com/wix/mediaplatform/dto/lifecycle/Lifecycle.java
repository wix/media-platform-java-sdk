package com.wix.mediaplatform.dto.lifecycle;

public class Lifecycle {
    private Integer age;
    private Action action;

    public Lifecycle() {}

    public String getAction() {
        return action.getValue();
    }

    public Lifecycle setAction(Action action) {
        this.action = action;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Lifecycle setAge(Integer age) {
        this.age = age;
        return this;
    }

    public enum Action {
        DELETE("delete");

        private final String action;

        Action(String action) {
            this.action = action;
        }

        public String getValue() {
            return action;
        }

        public static Action fromString(String asString) {
            for (Lifecycle.Action type: Lifecycle.Action .values()) {
                if (type.getValue().equals(asString)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid value for job type: " + asString);
        }
    }
}
