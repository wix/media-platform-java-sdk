package com.wix.mediaplatform.v6.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.exception.ResourceNotFoundException;
import com.wix.mediaplatform.v6.exception.UnauthorizedException;
import com.wix.mediaplatform.v6.service.RestResponse;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;


public class AuthenticatedHTTPClient {

    private static final String APPLICATION_JSON = "application/json";

    private final Authenticator authenticator;

    private final OkHttpClient httpClient;

    private final ObjectMapper objectMapper;

    public AuthenticatedHTTPClient(Authenticator authenticator, OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.authenticator = authenticator;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public <P> P get(String url, Class<P> clazz) throws MediaPlatformException {
        return get(url, null, clazz);
    }

    public <P> P get(String url, @Nullable Map<String, String> params, Class<P> clazz) throws MediaPlatformException {

        HttpUrl withQuery = appendQuery(params, url);

        Request request = defaultBuilder()
                .get()
                .url(withQuery)
                .build();

        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }

    public <P> P post(String url, Object payload, Class<P> clazz) throws MediaPlatformException {

        Request request;
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse(APPLICATION_JSON),
                    objectMapper.writeValueAsBytes(payload)
            );
            request = defaultBuilder()
                    .post(body)
                    .url(url)
                    .build();
        } catch (JsonProcessingException e) {
            throw new MediaPlatformException(e.getMessage());
        }

        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }

    public <P> P postForm(String url, String mimeType, byte[] content, Map<String, String> params, Class<P> clazz) throws MediaPlatformException {

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "file-name",
                        RequestBody.create(MediaType.parse(mimeType), content)
                );

        params.forEach(bodyBuilder::addFormDataPart);

        Request request = defaultBuilder()
                .post(bodyBuilder.build())
                .url(url)
                .build();

        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }

    public <P> P put(String url, Object payload, @Nullable Map<String, String> params, Class<P> clazz) throws MediaPlatformException {

        HttpUrl withQuery = appendQuery(params, url);

        Request request;
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse(APPLICATION_JSON),
                    objectMapper.writeValueAsBytes(payload)
            );
            request = defaultBuilder()
                    .put(body)
                    .url(withQuery)
                    .build();
        } catch (JsonProcessingException e) {
            throw new MediaPlatformException(e.getMessage());
        }

        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }

    public <P> P delete(String url, Class<P> clazz) throws MediaPlatformException {
        return delete(url, null, clazz);
    }

    public <P> P delete(String url, @Nullable Map<String, String> params, Class<P> clazz) throws MediaPlatformException {

        HttpUrl withQuery = appendQuery(params, url);

        Request request = defaultBuilder()
                .delete()
                .url(withQuery)
                .build();

        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }

    private Request.Builder defaultBuilder() {

        String authHeader = authenticator.getHeader();

        return new Request.Builder()
                .header("User-Agent", "WixMP Java SDK 6.x")
                .addHeader("Accept", APPLICATION_JSON)
                .addHeader("Authorization", authHeader);
    }

    private HttpUrl appendQuery(@Nullable Map<String, String> params, String url) throws MediaPlatformException {

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (null == httpUrl) {
            throw new MediaPlatformException("bad url");
        }

        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();

        if (null != params) {
            params.forEach(urlBuilder::setQueryParameter);
        }

        return urlBuilder.build();
    }

    private <P> P handleResponse(Response response, @Nullable Class<P> clazz) throws MediaPlatformException {

        int statusCode = response.code();

        if (statusCode == 401 || statusCode == 403) {
            throw new UnauthorizedException();
        }

        if (statusCode == 404) {
            throw new ResourceNotFoundException(response.toString());
        }

        if (statusCode < 200 || statusCode > 299) {
            throw new MediaPlatformException(response.toString(), statusCode);
        }

        if (null == response.body()) {
            throw new MediaPlatformException("empty response");
        }

        try {
            if (clazz != null) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(RestResponse.class, clazz);
                RestResponse<P> restResponse = objectMapper.readValue(response.body().byteStream(), javaType);
                restResponse.throwForErrorCode();
                return restResponse.getPayload();
            } else {
                RestResponse restResponse = objectMapper.readValue(response.body().byteStream(), RestResponse.class);
                restResponse.throwForErrorCode();
                return null;
            }
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }
}
