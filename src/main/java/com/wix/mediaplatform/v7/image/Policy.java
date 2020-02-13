package com.wix.mediaplatform.v7.image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class Policy {

    private String path;

    private Integer maxHeight;

    private Integer maxWidth;

    public String getPath() {
        return path;
    }

    public Policy setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public Policy setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public Policy setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public Map<String, Object> toClaims() {
        Map<String, Object> policy = new HashMap<>();
        if (null != path) {
            policy.put("path", path);
        }
        if (null != maxHeight) {
            policy.put("height", String.format("<=%s", maxHeight));
        }
        if (null != maxWidth) {
            policy.put("width", String.format("<=%s", maxWidth));
        }

        List<Map<String, Object>> inner = newArrayList(policy);
        List<List<Map<String, Object>>> outer = newArrayList();
        outer.add(inner);

        HashMap<String, Object> claim = new HashMap<>();
        claim.put("obj", outer);

        return claim;
    }
}
