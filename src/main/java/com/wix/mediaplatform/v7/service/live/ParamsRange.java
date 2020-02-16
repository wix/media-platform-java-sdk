package com.wix.mediaplatform.v7.service.live;

public class ParamsRange {

    private ParamsOption maxValues;

    private ParamsOption minValues;

    public ParamsRange() {
    }

    public ParamsOption getMaxValues() {
        return maxValues;
    }

    public ParamsRange setMaxValues(ParamsOption maxValues) {
        this.maxValues = maxValues;
        return this;
    }

    public ParamsOption getMinValues() {
        return minValues;
    }

    public ParamsRange setMinValues(ParamsOption minValues) {
        this.minValues = minValues;
        return this;
    }
}
