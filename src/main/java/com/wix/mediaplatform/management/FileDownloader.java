package com.wix.mediaplatform.management;

import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.authentication.NS;
import com.wix.mediaplatform.authentication.Token;
import com.wix.mediaplatform.authentication.VERB;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.request.DownloadUrlRequest;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileDownloader {

    private final Authenticator authenticator;

    private final Configuration configuration;

    public FileDownloader(Configuration configuration, Authenticator authenticator) {
        this.authenticator = authenticator;
        this.configuration = configuration;
    }

    public String getDownloadUrl(String path) {
        return getDownloadUrl(path, null);
    }

    public String getDownloadUrl(String path, @Nullable DownloadUrlRequest downloadUrlRequest) {
        Map<String, Object> payload = newHashMap();
        payload.put("path", path);
        Token token = new Token();
        if (downloadUrlRequest != null) {
            if (downloadUrlRequest.getTtl() != null) {
                token.setExpiration((System.currentTimeMillis() / 1000L) + downloadUrlRequest.getTtl());
            }
            if (downloadUrlRequest.getOnExpireRedirectTo() != null) {
                payload.put("onExpireRedirectTo", downloadUrlRequest.getOnExpireRedirectTo());
            }
            if (downloadUrlRequest.getAttachment() != null) {
                Map<String, Object> attachment = newHashMap();
                payload.put("attachment", attachment);
                if (downloadUrlRequest.getAttachment().getFilename() != null) {
                    attachment.put("filename", downloadUrlRequest.getAttachment().getFilename());
                }
            }
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> additionalClaims = (Map<String, Object>) newHashMap().put("payload", payload);
        token.setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .addVerb(VERB.FILE_DOWNLOAD)
                .setAdditionalClaims(additionalClaims);

        String signedToken = authenticator.encode(token);

        return "https://" + configuration.getDomain() + "/_api/download/file?downloadToken=" + signedToken;
    }
}
