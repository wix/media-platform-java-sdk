package com.wix.mediaplatform.http;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.exception.ResourceNotFoundException;
import com.wix.mediaplatform.exception.UnauthorizedException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.apache.http.HttpHeaders.*;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class AuthenticatedHTTPClient {

    private static final Header ACCEPT_JSON = new BasicHeader(ACCEPT, APPLICATION_JSON.getMimeType());
    private static final Header CONTENT_TYPE_JSON = new BasicHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType());

    private final Authenticator authenticator;
    private final HttpClient httpClient;
    private final Gson gson;

    public AuthenticatedHTTPClient(Authenticator authenticator, HttpClient httpClient, Gson gson) {
        this.authenticator = authenticator;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public <T> T get(String url, @Nullable Map<String, String> params, Type responseType) throws IOException, URISyntaxException, MediaPlatformException {

        String authHeader = authenticator.getHeader();

        String fullUrl = appendQueryString(url, params);
        HttpGet request = new HttpGet(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(AUTHORIZATION, authHeader);

        HttpResponse response = httpClient.execute(request);

        return getGsonResponse(response, responseType);
    }

    public <T> T post(String url, Object payload, @Nullable Map<String, String> params, Type responseType) throws IOException, MediaPlatformException, URISyntaxException {

        String authHeader = authenticator.getHeader();

        String fullUrl = appendQueryString(url, params);
        HttpPost request = new HttpPost(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(CONTENT_TYPE_JSON);
        request.addHeader(AUTHORIZATION, authHeader);
        if (payload != null) {
            String json = gson.toJson(payload);
            StringEntity entity = new StringEntity(json);
            request.setEntity(entity);
        }

        HttpResponse response = httpClient.execute(request);

        if (responseType == null) {
            assertResponseStatus(response);
            return null;
        }

        return getGsonResponse(response, responseType);
    }

    public <T> T post(String url, HttpEntity form, Type responseType) throws IOException, MediaPlatformException {
        HttpPost request = new HttpPost(url);
        request.addHeader(ACCEPT_JSON);
        request.setEntity(form);

        HttpResponse response = httpClient.execute(request);

        return getGsonResponse(response, responseType);
    }

    public <T> T put(String url, Object payload, @Nullable Map<String, String> params, Type responseType) throws IOException, MediaPlatformException, URISyntaxException {

        String authHeader = authenticator.getHeader();

        String fullUrl = appendQueryString(url, params);
        HttpPut request = new HttpPut(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(CONTENT_TYPE_JSON);
        request.addHeader(AUTHORIZATION, authHeader);
        String json = gson.toJson(payload);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);

        return getGsonResponse(response, responseType);
    }

    public <T> T delete(String url, @Nullable Map<String, String> params, @Nullable Type responseType) throws IOException, MediaPlatformException, URISyntaxException {

        String authHeader = authenticator.getHeader();

        String fullUrl = appendQueryString(url, params);
        HttpDelete request = new HttpDelete(fullUrl);
        request.addHeader(ACCEPT_JSON);
        request.addHeader(AUTHORIZATION, authHeader);

        HttpResponse response = httpClient.execute(request);

        if (responseType == null) {
            assertResponseStatus(response);
            return null;
        }

        return getGsonResponse(response, responseType);
    }

    private <T> T getGsonResponse(HttpResponse response, Type responseType) throws IOException, MediaPlatformException {
        try {
            T gsonResponse = gson.fromJson(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8),
                    responseType);

            if (gsonResponse instanceof RestResponse) {
                RestResponse restResponse = (RestResponse) gsonResponse;
                restResponse.throwForErrorCode();
            }

            assertResponseStatus(response);
            return gsonResponse;
        }
        catch (JsonIOException|JsonSyntaxException ex) {
            assertResponseStatus(response);
            throw ex;
        }
        finally {
            tryToCloseResponse(response);
        }
    }

    private void assertResponseStatus(HttpResponse response) throws IOException, MediaPlatformException {
        try {
            if (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 403) {
                throw new UnauthorizedException();
            }

            if (response.getStatusLine().getStatusCode() == 404) {
                throw new ResourceNotFoundException(response.toString());
            }

            if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 299) {
                throw new MediaPlatformException(response.toString());
            }
        }
        finally {
            tryToCloseResponse(response);
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

    private void tryToCloseResponse(HttpResponse response) throws IOException {
        if (response instanceof CloseableHttpResponse) {
            ((CloseableHttpResponse) response).close();
        }
    }
}
