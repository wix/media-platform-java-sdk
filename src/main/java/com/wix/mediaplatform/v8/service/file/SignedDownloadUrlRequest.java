package com.wix.mediaplatform.v8.service.file;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wix.mediaplatform.v8.auth.Authenticator;
import com.wix.mediaplatform.v8.auth.NS;
import com.wix.mediaplatform.v8.auth.Token;
import com.wix.mediaplatform.v8.auth.VERB;
import com.wix.mediaplatform.v8.exception.MediaPlatformException;
import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.net.UrlEscapers.urlFormParameterEscaper;

public class SignedDownloadUrlRequest extends MediaPlatformRequest<String> {

    private String path;

    private Integer ttl = 600; // seconds

    private Attachment attachment;

    private String onExpireRedirectTo;

    @JsonIgnore
    private final String appId;

    @JsonIgnore
    private final Authenticator authenticator;

    public SignedDownloadUrlRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl,
                                    Authenticator authenticator, String appId) {
        super(authenticatedHTTPClient,
                null,
                baseUrl.replaceFirst(".appspot.com", ".wixmp.com")
                        .replaceFirst("/_api", ""),
                String.class);

        this.appId = appId;
        this.authenticator = authenticator;
    }

    public String execute() throws MediaPlatformException {
        validate();

        Map<String, String> pathMatcher = newHashMap();
        pathMatcher.put("path", path);

        List<Map<String, String>> andClauses = newArrayList();
        andClauses.add(pathMatcher);

        List<List<Map<String, String>>> orClauses = newArrayList();
        orClauses.add(andClauses);

        Token token = new Token()
                .setExpiration((System.currentTimeMillis() / 1000L) + ttl)
                .setIssuer(NS.APPLICATION + appId)
                .setSubject(NS.APPLICATION + appId)
                .addVerb(VERB.FILE_DOWNLOAD)
                .setObject(orClauses);

        if (onExpireRedirectTo != null) {
            Map<String, Object> additionalClaims = newHashMap();
            additionalClaims.put("red", onExpireRedirectTo);
            token.setAdditionalClaims(additionalClaims);
        }

        String signedToken = authenticator.encode(token);

        StringBuilder builder = new StringBuilder(url).append(path).append("?token=").append(signedToken);
        if (attachment != null && attachment.getFilename() != null) {
            builder.append("&filename=").append(urlFormParameterEscaper().escape(attachment.getFilename()));
        }

        return builder.toString();
    }

    public String getPath() {
        return path;
    }

    public SignedDownloadUrlRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getTtl() {
        return ttl;
    }

    public SignedDownloadUrlRequest setTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public SignedDownloadUrlRequest setAttachment(Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public String getOnExpireRedirectTo() {
        return onExpireRedirectTo;
    }

    public SignedDownloadUrlRequest setOnExpireRedirectTo(String onExpireRedirectTo) {
        this.onExpireRedirectTo = onExpireRedirectTo;
        return this;
    }
}
