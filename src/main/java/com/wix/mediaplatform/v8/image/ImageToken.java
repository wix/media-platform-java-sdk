package com.wix.mediaplatform.v8.image;

import com.wix.mediaplatform.v8.auth.Token;

import java.util.Map;

public class ImageToken extends Token {

    private static final String VERB = "urn:service:image.operations";

    private Policy policy;
    private Watermark watermark;

    public ImageToken() {
        super();
        this.addVerb(VERB);
    }

    public ImageToken(Policy policy, Watermark watermark) {
        super();
        this.addVerb(VERB);
        this.policy = policy;
        this.watermark = watermark;
    }

    public Policy getPolicy() {
        return policy;
    }

    public ImageToken setPolicy(Policy policy) {
        this.policy = policy;
        return this;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public ImageToken setWatermark(Watermark watermark) {
        this.watermark = watermark;
        return this;
    }

    public ImageToken cacheableUntil(Long expiration) {
        this.setTokenId(expiration.toString());
        this.setExpiration(expiration);
        return (ImageToken) this.setIssuedAt(null);
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
