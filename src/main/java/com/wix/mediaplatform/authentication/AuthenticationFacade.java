package com.wix.mediaplatform.authentication;

import com.auth0.jwt.JWTSigner;
import com.google.common.cache.Cache;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.wix.mediaplatform.configuration.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static com.wix.mediaplatform.http.Constants.AUTHORIZATION_HEADER;
import static com.wix.mediaplatform.http.Constants.DO_NOT_CACHE;

public class AuthenticationFacade {

    private static final String AUTH_ENDPOINT = "/apps/auth/token";
    private static final String MEDIA_PLATFORM_TOKEN_PREFIX = "MCLOUDTOKEN ";

    private final Configuration configuration;
    private final Gson gson;
    private final OkHttpClient client;
    private final JWTSigner signer; //TODO: move to bootstrap, seems to be thread safe

    private final SecureRandom random = new SecureRandom();
    private final Cache<String, String> tokenCache;
    private final String authUrl;

    /**
     * @param configuration The Media Platform client config
     * @param httpClient The global http client
     * @param gson The JSON serializer
     */
    public AuthenticationFacade(Configuration configuration, OkHttpClient httpClient, Gson gson) {

        this.configuration = configuration;
        this.client = httpClient;
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
     */
    @Nullable
    public String getHeader(String userId) throws IOException {

        String token = getToken(userId);
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
    private String getToken(String userId) throws IOException {

        String token = this.tokenCache.getIfPresent(userId);
        if (token != null) {
            return token;
        }

        long now = System.currentTimeMillis() / 1000;
        byte[] nonce = new byte[6];
        random.nextBytes(nonce);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("sub", "user:" + userId);
        claims.put("iss", "app:" + this.configuration.getAppId());
        claims.put("exp", now + 60);
        claims.put("iat", now);
        claims.put("jti", BaseEncoding.base16().encode(nonce));
        String authHeader = "APP " + signer.sign(claims);

        Request request = new Request.Builder()
                .cacheControl(DO_NOT_CACHE)
                .url(this.authUrl)
                .addHeader(AUTHORIZATION_HEADER, authHeader)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.toString());
        }
        String body = response.body().toString();
        token = gson.fromJson(body, String.class);

        if (token != null) {
            this.tokenCache.put(userId, token);
        }
        return token;
    }
}
