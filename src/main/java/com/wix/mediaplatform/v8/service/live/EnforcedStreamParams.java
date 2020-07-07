package com.wix.mediaplatform.v8.service.live;

import java.util.ArrayList;
import java.util.List;

public class EnforcedStreamParams {

    private ParamsRange paramsRange;

    private List<ParamsOption> paramsOptions = new ArrayList<>();

    public EnforcedStreamParams() {
    }

    public ParamsRange getParamsRange() {
        return paramsRange;
    }

    public EnforcedStreamParams setParamsRange(ParamsRange paramsRange) {
        this.paramsRange = paramsRange;
        return this;
    }

    public List<ParamsOption> getParamsOptions() {
        return paramsOptions;
    }

    public EnforcedStreamParams addParamsOption(ParamsOption paramsOption) {
        paramsOptions.add(paramsOption);
        return this;
    }

    public EnforcedStreamParams setParamsOptions(List<ParamsOption> paramsOptions) {
        this.paramsOptions = paramsOptions;
        return this;
    }
}
