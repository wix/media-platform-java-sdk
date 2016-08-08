package com.wix.mediaplatform.http;

import com.google.gson.Gson;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.exception.UnauthorizedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.nio.client.HttpAsyncClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.wix.mediaplatform.http.Constants.ACCEPT_JSON;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class AuthenticatedHTTPClient {

    private final AuthenticationFacade authenticationFacade;
    private final HttpAsyncClient httpClient;
    private final Gson gson;

    public AuthenticatedHTTPClient(AuthenticationFacade authenticationFacade, HttpAsyncClient httpClient, Gson gson) {
        this.authenticationFacade = authenticationFacade;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public <T> T get(String userId, String url, @Nullable Map<String, String> params, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {

        String authHeader = authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        URIBuilder builder = new URIBuilder(url);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpGet request = new HttpGet(url);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(AUTHORIZATION, authHeader);

        HttpResponse response;
        try {
            response = httpClient.execute(request, null).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException(e);
        }

        if (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 404) {
            authenticationFacade.invalidateToken(userId);
            throw new UnauthorizedException();
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }

        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    public <T> T postMultipartAnonymous(String url, HttpEntity form, Type responseType) throws IOException {

        HttpPost request = new HttpPost(url);
        request.addHeader(ACCEPT_JSON);
        request.setEntity(form);

        HttpResponse response;
        try {
            response = httpClient.execute(request, null).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException(e);
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }

        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }
}
