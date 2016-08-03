package com.wix.mediaplatform.authentication;

import com.auth0.jwt.JWTSigner;
import com.google.common.cache.Cache;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.wix.mediaplatform.configuration.Configuration;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;

public class AuthenticatedHttpClient {

    private static final String AUTH_ENDPOINT = "/apps/auth/token";
    private static final String MEDIA_PLATFORM_TOKEN_PREFIX = "MCLOUDTOKEN ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final CacheControl DO_NOT_CACHE = new CacheControl.Builder()
            .noCache()
            .noStore()
            .build();

    private final OkHttpClient client = new OkHttpClient.Builder()
            .build();

    private final SecureRandom random = new SecureRandom();

    private final Cache<String, String> tokenCache;

    private final Configuration configuration;
    private final Gson gson = new Gson(); //TODO: move to bootstrap, it is thread safe
    private final JWTSigner signer;
    private final String authUrl;

    /**
     * @param configuration The Media Platform client config
     */
    public AuthenticatedHttpClient(Configuration configuration) {

        this.configuration = configuration;

        this.signer = new JWTSigner(configuration.getSharedSecret());
        this.tokenCache = newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .maximumSize(10000)
                .build();

        this.authUrl = "https://" + configuration.getDomain() + AUTH_ENDPOINT;
    }

    public String getHeader(String userId) {
        return MEDIA_PLATFORM_TOKEN_PREFIX + getToken(userId);
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
     */
    @Nullable
    private String getToken(String userId) {

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

        try {
            Response response = client.newCall(request).execute();
            String body = response.body().toString();
            token = gson.fromJson(body, String.class);
            this.tokenCache.put(userId, token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }
}
