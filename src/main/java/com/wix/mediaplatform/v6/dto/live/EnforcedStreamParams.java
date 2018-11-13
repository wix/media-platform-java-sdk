package com.wix.mediaplatform.v6.dto.live;

import java.util.ArrayList;

public class EnforcedStreamParams {

    private ParamsRange paramsRange;
    private ArrayList<ParamsOption> paramsOptions;

    public EnforcedStreamParams() {
        paramsOptions = new ArrayList<ParamsOption>();
    }

    public ParamsRange getParamsRange() {
        return paramsRange;
    }

    public EnforcedStreamParams setParamsRange(ParamsRange paramsRange) {
        this.paramsRange = paramsRange;
        return this;
    }

    public ArrayList<ParamsOption> getParamsOptions() {
        return paramsOptions;
    }

    public EnforcedStreamParams addParamsOption(ParamsOption paramsOption) {
        paramsOptions.add(paramsOption);
        return this;
    }

    public EnforcedStreamParams setParamsOptions(ArrayList<ParamsOption> paramsOptions) {
        this.paramsOptions = paramsOptions;
        return this;
    }
}
