package com.wix.mediaplatform.dto.download;

import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class GetSecureUrlRequest {

    private String fileId;

    private Set<String> encoding = newHashSet();

    private Integer expiresIn;

    private String saveAs;

    private String expiredRedirectUrl;

    private String resize;

    private String namespace;

    public GetSecureUrlRequest() {
    }

    public GetSecureUrlRequest(String fileId, Set<String> encoding, @Nullable Integer expiresIn, @Nullable String saveAs, @Nullable String expiredRedirectUrl) {
        this.fileId = fileId;
        this.encoding = encoding;
        this.expiresIn = expiresIn;
        this.saveAs = saveAs;
        this.expiredRedirectUrl = expiredRedirectUrl;
    }

    public GetSecureUrlRequest(String fileId, Set<String> encoding, @Nullable Integer expiresIn, @Nullable String saveAs, @Nullable String expiredRedirectUrl, @Nullable String resize) {
        this(fileId, encoding, expiresIn, saveAs, expiredRedirectUrl);
        this.resize = resize;
    }

    public GetSecureUrlRequest(String fileId, Set<String> encoding, @Nullable Integer expiresIn, @Nullable String saveAs, @Nullable String expiredRedirectUrl, @Nullable String resize, @Nullable String namespace) {
        this(fileId, encoding, expiresIn, saveAs, expiredRedirectUrl, resize);
        this.namespace = namespace;
    }

    public GetSecureUrlRequest setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public GetSecureUrlRequest setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public GetSecureUrlRequest setEncoding(Set<String> encoding) {
        this.encoding = encoding;
        return this;
    }

    public GetSecureUrlRequest addEncoding(String encoding) {
        this.encoding.add(encoding);
        return this;
    }

    public GetSecureUrlRequest setSaveAs(String saveAs) {
        this.saveAs = saveAs;
        return this;
    }

    public GetSecureUrlRequest setExpiredRedirectUrl(String expiredRedirectUrl) {
        this.expiredRedirectUrl = expiredRedirectUrl;
        return this;
    }

    public GetSecureUrlRequest setResize(String resize) {
        this.resize = resize;
        return this;
    }

    public GetSecureUrlRequest setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public Set<String> getEncoding() {
        return encoding;
    }

    @Nullable
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @Nullable
    public String getSaveAs() {
        return saveAs;
    }

    @Nullable
    public String getExpiredRedirectUrl() {
        return expiredRedirectUrl;
    }

    @Nullable
    public String getResize() {
        return resize;
    }

    @Nullable
    public String getNamespace() {
        return namespace;
    }
}
