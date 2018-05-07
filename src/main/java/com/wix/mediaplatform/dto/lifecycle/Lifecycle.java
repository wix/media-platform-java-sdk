package com.wix.mediaplatform.dto.lifecycle;

public class Lifecycle {
    private Integer age;
    private Action action;

    public Lifecycle() {}

    public Action getAction() {
        return action;
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

        public String getAction() {
            return action;
        }
    }
}
