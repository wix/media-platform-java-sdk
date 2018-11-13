package com.wix.mediaplatform.v6.auth;

import com.google.common.collect.Lists;
import com.wix.mediaplatform.v6.auth.jwt.Constants;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.io.BaseEncoding.base16;

public class Token {

    private static final SecureRandom secureRandom = new SecureRandom();

    private String issuer;
    private String subject;
    private String object;
    private List<String> verbs = newArrayList();
    private Long issuedAt;
    private Long expiration;
    private String tokenId;
    private Map<String, Object> additionalClaims;

    public Token() {
        this.issuedAt = (System.currentTimeMillis() / 1000L) - 10;
        this.expiration = (System.currentTimeMillis() / 1000L) + 600;

        byte[] bytes = new byte[6];
        secureRandom.nextBytes(bytes);
        this.tokenId = base16().encode(bytes);
    }

    public Token(Map<String, Object> claims) {
        this.issuer = (claims.get(Constants.ISSUER) == null) ? null :
                claims.get(Constants.ISSUER).toString();
        this.subject = (claims.get(Constants.SUBJECT) == null) ? null :
                claims.get(Constants.SUBJECT).toString();
        this.object = (claims.get(Constants.OBJECT) == null) ? null :
                claims.get(Constants.OBJECT).toString();
        this.verbs = (claims.get(Constants.AUDIENCE) == null) ? Lists.newArrayList() :
                (ArrayList) claims.get(Constants.AUDIENCE);
        this.issuedAt = (claims.get(Constants.ISSUED_AT) == null) ? null :
                ((Integer) claims.get(Constants.ISSUED_AT)).longValue();
        this.expiration = (claims.get(Constants.EXPIRATION) == null) ? null :
                ((Integer) claims.get(Constants.EXPIRATION)).longValue();
        this.tokenId = (claims.get(Constants.IDENTIFIER) == null) ? null :
                claims.get(Constants.IDENTIFIER).toString();
    }

    public Token setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public Token setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Token setObject(String object) {
        this.object = object;
        return this;
    }

    public Token setVerbs(List<String> verbs) {
        this.verbs = verbs;
        return this;
    }

    public Token addVerb(String verb) {
        this.verbs.add(verb);
        return this;
    }

    public Token setExpiration(Long expiration) {
        this.expiration = expiration;
        return this;
    }

    public Token setAdditionalClaims(Map<String, Object> additionalClaims) {
        this.additionalClaims = additionalClaims;
        return this;
    }

    public static SecureRandom getSecureRandom() {
        return secureRandom;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getSubject() {
        return subject;
    }

    public String getObject() {
        return object;
    }

    public List<String> getVerbs() {
        return verbs;
    }

    public Long getIssuedAt() {
        return issuedAt;
    }

    public Long getExpiration() {
        return expiration;
    }

    public String getTokenId() {
        return tokenId;
    }

    @Nullable
    public Map<String, Object> getAdditionalClaims() {
        return additionalClaims;
    }

    public Map<String, Object> toClaims() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.SUBJECT, subject);
        claims.put(Constants.ISSUER, issuer);
        claims.put(Constants.EXPIRATION, expiration);
        claims.put(Constants.ISSUED_AT, issuedAt);
        claims.put(Constants.IDENTIFIER, tokenId);
        claims.put(Constants.AUDIENCE, verbs);
        if (additionalClaims != null) {
            claims.putAll(additionalClaims);
        }

        return claims;
    }
}
