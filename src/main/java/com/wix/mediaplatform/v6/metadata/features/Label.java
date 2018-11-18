package com.wix.mediaplatform.v6.metadata.features;

public class Label {

    private String name;

    private Float score;

    public Label() {
    }

    public String getName() {
        return name;
    }

    public Float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Label{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
