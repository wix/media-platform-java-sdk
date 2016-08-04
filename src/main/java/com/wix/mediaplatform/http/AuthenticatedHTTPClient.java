package com.wix.mediaplatform.http;

import com.google.gson.Gson;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.exception.UnauthorizedException;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;

import static com.wix.mediaplatform.http.Constants.AUTHORIZATION_HEADER;
import static com.wix.mediaplatform.http.Constants.DO_NOT_CACHE;

public class AuthenticatedHTTPClient {

    private final AuthenticationFacade authenticationFacade;
    private final OkHttpClient client;
    private final Gson gson;

    public AuthenticatedHTTPClient(AuthenticationFacade authenticationFacade, OkHttpClient client, Gson gson) {
        this.authenticationFacade = authenticationFacade;
        this.client = client;
        this.gson = gson;
    }

    public <T> T execute(String method, String userId, String url, @Nullable Object payload, Type responseType) throws IOException, UnauthorizedException {

        String authHeader = this.authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(payload));

        Request request = new Request.Builder()
                .method(method, requestBody)
                .cacheControl(DO_NOT_CACHE)
                .url(url)
                .addHeader(AUTHORIZATION_HEADER, authHeader)
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 401 || response.code() == 404) {
            this.authenticationFacade.invalidateToken(userId);
            throw new UnauthorizedException();
        }

        if (!response.isSuccessful()) {
            throw new IOException(response.toString());
        }

        String body = response.body().toString();
        return gson.fromJson(body, responseType);
    }
}
