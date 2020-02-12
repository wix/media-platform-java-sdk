package com.wix.mediaplatform.v7.image;

import com.wix.mediaplatform.v7.auth.Token;

import java.util.Map;

public class ImageToken extends Token {
    private Policy policy;
    private Watermark watermark;

    public ImageToken(Policy policy, Watermark watermark) {
        super();
        this.policy = policy;
        this.watermark = watermark;
    }

    @Override
    public Map<String, Object> toClaims() {
        Map<String, Object> claims = super.toClaims();

        if (null != policy) {
            claims.putAll(policy.toClaims());
        }

        if (null != watermark) {
            claims.putAll(watermark.toClaims());
        }

        return claims;
    }
}
