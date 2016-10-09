package com.wix.mediaplatform.http;

import com.google.gson.Gson;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.exception.UnauthorizedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.wix.mediaplatform.http.Constants.ACCEPT_JSON;
import static com.wix.mediaplatform.http.Constants.CONTENT_TYPE_JSON;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class AuthenticatedHTTPClient {

    private final AuthenticationFacade authenticationFacade;
    private final HttpClient httpClient;
    private final Gson gson;

    public AuthenticatedHTTPClient(AuthenticationFacade authenticationFacade, HttpClient httpClient, Gson gson) {
        this.authenticationFacade = authenticationFacade;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public <T> T get(String userId, String url, @Nullable Map<String, String> params, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {

        String authHeader = authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        String fullUrl = appendQueryString(url, params);
        HttpGet request = new HttpGet(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(AUTHORIZATION, authHeader);

        HttpResponse response = httpClient.execute(request);

        assertResponseStatus(userId, response);

        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    @Nullable
    public <T> T post(String userId, String url, Object payload, Map<String, String> params, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {
        String authHeader = authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        String fullUrl = appendQueryString(url, params);
        HttpPost request = new HttpPost(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(CONTENT_TYPE_JSON);
        request.addHeader(AUTHORIZATION, authHeader);
        String json = gson.toJson(payload);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);

        assertResponseStatus(userId, response);

        if (responseType == null) {
            return null;
        }
        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    public <T> T postWithSelfSignedToken(String userId, String url, Object payload, Map<String, Object> additionalClaims, Type responseType) throws IOException, UnauthorizedException {
        String authHeader = authenticationFacade.getSelfSignedHeader(userId, additionalClaims);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        HttpPost request = new HttpPost(url);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(CONTENT_TYPE_JSON);
        request.addHeader(AUTHORIZATION, authHeader);
        if (payload != null) {
            String json = gson.toJson(payload);
            StringEntity entity = new StringEntity(json);
            request.setEntity(entity);
        }

        HttpResponse response = httpClient.execute(request);

        assertResponseStatus(userId, response);

        if (responseType == null) {
            return null;
        }
        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    public <T> T put(String userId, String url, Object payload, @Nullable Map<String, String> params, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {

        String authHeader = authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        String fullUrl = appendQueryString(url, params);
        HttpPut request = new HttpPut(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(CONTENT_TYPE_JSON);
        request.addHeader(AUTHORIZATION, authHeader);
        String json = gson.toJson(payload);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);

        assertResponseStatus(userId, response);

        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    @Nullable
    public <T> T delete(String userId, String url, @Nullable Map<String, String> params, @Nullable Type responseType) throws IOException, UnauthorizedException, URISyntaxException {
        String authHeader = authenticationFacade.getHeader(userId);
        if (authHeader == null) {
            throw new UnauthorizedException();
        }

        String fullUrl = appendQueryString(url, params);
        HttpDelete request = new HttpDelete(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(AUTHORIZATION, authHeader);

        HttpResponse response = httpClient.execute(request);

        assertResponseStatus(userId, response);

        if (responseType == null) {
            return null;
        }
        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    public <T> T postMultipartAnonymous(String url, HttpEntity form, Type responseType) throws IOException {

        HttpPost request = new HttpPost(url);
        request.addHeader(ACCEPT_JSON);
        request.setEntity(form);

        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }

        return gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), responseType);
    }

    private void assertResponseStatus(String userId, HttpResponse response) throws UnauthorizedException, IOException {
        if (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 403) {
            authenticationFacade.invalidateToken(userId);
            throw new UnauthorizedException();
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
            throw new IOException(response.toString());
        }
    }

    private String appendQueryString(String url, @Nullable Map<String, String> params) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
        }

        return builder.toString();
    }
}
