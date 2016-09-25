package com.wix.mediaplatform.authentication;

import com.auth0.jwt.JWTSigner;
import com.google.common.cache.Cache;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.wix.mediaplatform.authentication.dto.GetAuthTokenResponse;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.exception.UnauthorizedException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static com.wix.mediaplatform.http.Constants.ACCEPT_JSON;
import static com.wix.mediaplatform.jwt.Constants.*;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationFacade {

    private static final String AUTH_ENDPOINT = "/apps/auth/token";
    private static final String MEDIA_PLATFORM_TOKEN_PREFIX = "MCLOUDTOKEN ";

    private final Configuration configuration;
    private final Gson gson;
    private final HttpClient httpClient;
    private final JWTSigner signer; //TODO: move to bootstrap, seems to be thread safe

    private final SecureRandom random = new SecureRandom();
    private final Cache<String, String> tokenCache;
    private final String authUrl;

    /**
     * @param configuration The Media Platform httpClient config
     * @param httpClient The global http httpClient
     * @param gson The JSON serializer
     */
    public AuthenticationFacade(Configuration configuration, HttpClient httpClient, Gson gson) {

        this.configuration = configuration;
        this.httpClient = httpClient;
        this.gson = gson;

        this.signer = new JWTSigner(configuration.getSharedSecret());
        this.tokenCache = newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .maximumSize(10000)
                .build();

        this.authUrl = "https://" + configuration.getDomain() + AUTH_ENDPOINT;
    }

    /**
     * @param userId Your user id, that the token is generated for
     * @return The authorization header, or null if the authentication failed
     * @throws IOException If the request failed
     * @throws UnauthorizedException If the authentication failed
     */
    @Nullable
    public String getHeader(String userId) throws IOException, UnauthorizedException {
        String token = getToken(userId);
        if (token == null) {
            return null;
        }

        return MEDIA_PLATFORM_TOKEN_PREFIX + token;
    }

    /**
     * @param userId Your user id, that the token is generated for
     * @param additionalClaims optional claims to be added to the token
     * @return The authorization header, or null if the authentication failed
     * @throws IOException If the request failed
     * @throws UnauthorizedException If the authentication failed
     */
    @Nullable
    public String getProvisionalHeader(String userId, Map<String, Object> additionalClaims) throws IOException, UnauthorizedException {
        String token = getProvisionalToken(userId, additionalClaims);
        if (token == null) {
            return null;
        }

        return MEDIA_PLATFORM_TOKEN_PREFIX + token;
    }

    /**
     * @param userId The user id of which the token was not accepted and needs to be removed from the cache
     */
    public void invalidateToken(String userId) {
        this.tokenCache.invalidate(userId);
    }

    /**
     * @param userId Your user id, that the token is generated for
     * @return The internal auth token, or null if the authentication failed
     * @throws IOException If the request failed
     */
    @Nullable
    private String getToken(String userId) throws IOException, UnauthorizedException {

        String token = this.tokenCache.getIfPresent(userId);
        if (token != null) {
            return token;
        }

        String authHeader = getAuthHeader(userId, null);

        HttpGet request = new HttpGet(authUrl);
        request.addHeader(AUTHORIZATION, authHeader);
        request.addHeader(ACCEPT_JSON);
        HttpResponse response;

        response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 403) {
            throw new UnauthorizedException();
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }

        token = gson.fromJson(
                new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), GetAuthTokenResponse.class)
                .getToken();

        if (token != null) {
            this.tokenCache.put(userId, token);
        }
        return token;
    }

    @Nullable
    private String getProvisionalToken(String userId, Map<String, Object> additionalClaims) throws IOException, UnauthorizedException {
        String authHeader = getAuthHeader(userId, additionalClaims);

        HttpGet request = new HttpGet(authUrl);
        request.addHeader(AUTHORIZATION, authHeader);
        request.addHeader(ACCEPT_JSON);
        HttpResponse response;

        response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 403) {
            throw new UnauthorizedException();
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }

        return gson.fromJson(
                new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), GetAuthTokenResponse.class)
                .getToken();
    }

    @NotNull
    private String getAuthHeader(String userId, Map<String, Object> additionalClaims) {
        long now = System.currentTimeMillis() / 1000;
        byte[] nonce = new byte[6];
        random.nextBytes(nonce);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, "user:" + userId);
        claims.put(ISSUER, "app:" + this.configuration.getAppId());
        claims.put(EXPIRATION, now + 60);
        claims.put(ISSUED_AT, now);
        claims.put(IDENTIFIER, BaseEncoding.base16().encode(nonce));
        if (additionalClaims != null) {
            claims.putAll(additionalClaims);
        }

        return "APP " + signer.sign(claims);
    }
}
