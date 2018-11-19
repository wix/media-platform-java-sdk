package com.wix.mediaplatform.v6.service.file;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.auth.NS;
import com.wix.mediaplatform.v6.auth.Token;
import com.wix.mediaplatform.v6.auth.VERB;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class DownloadUrlRequest extends MediaPlatformRequest<String> {

    private String path;

    private Integer ttl = 600; // seconds

    private Attachment attachment;

    private String onExpireRedirectTo;

    @JsonIgnore
    private String appId;

    @JsonIgnore
    private Authenticator authenticator;

    DownloadUrlRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, Authenticator authenticator,
                       String appId) {
        super(authenticatedHTTPClient, null, baseUrl + "/download/file", String.class);

        this.appId = appId;
        this.authenticator = authenticator;
    }

    public String execute() throws MediaPlatformException {

        validate();

        Map<String, Object> payload = newHashMap();
        payload.put("path", path);

        if (onExpireRedirectTo != null) {
            payload.put("onExpireRedirectTo", onExpireRedirectTo);
        }
        if (attachment != null) {
            Map<String, Object> attachmentMap = newHashMap();
            if (attachment.getFilename() != null) {
                attachmentMap.put("filename", attachment.getFilename());
            }

            payload.put("attachment", attachmentMap);
        }

        Map<String, Object> additionalClaims = newHashMap();
        additionalClaims.put("payload", payload);

        Token token = new Token()
                .setExpiration((System.currentTimeMillis() / 1000L) + ttl)
                .setIssuer(NS.APPLICATION + appId)
                .setSubject(NS.APPLICATION + appId)
                .addVerb(VERB.FILE_DOWNLOAD)
                .setAdditionalClaims(additionalClaims);

        String signedToken = authenticator.encode(token);

        return url + "?downloadToken=" + signedToken;
    }

    public String getPath() {
        return path;
    }

    public DownloadUrlRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getTtl() {
        return ttl;
    }

    public DownloadUrlRequest setTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public DownloadUrlRequest setAttachment(Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public String getOnExpireRedirectTo() {
        return onExpireRedirectTo;
    }

    public DownloadUrlRequest setOnExpireRedirectTo(String onExpireRedirectTo) {
        this.onExpireRedirectTo = onExpireRedirectTo;
        return this;
    }
}
