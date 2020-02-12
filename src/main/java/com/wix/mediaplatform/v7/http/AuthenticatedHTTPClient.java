package com.wix.mediaplatform.v7.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wix.mediaplatform.v7.auth.Authenticator;
import com.wix.mediaplatform.v7.exception.ForbiddenException;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.exception.ResourceNotFoundException;
import com.wix.mediaplatform.v7.exception.UnauthorizedException;
import com.wix.mediaplatform.v7.service.RestResponse;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class AuthenticatedHTTPClient {

    private static final String APPLICATION_JSON = "application/json";
    private static final MediaType MEDIA_TYPE = MediaType.parse(APPLICATION_JSON);
    private static final String USER_AGENT = "WixMP Java SDK 6.x";

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

        return doRequest(request, clazz);
    }

    public <P> P post(String url, Object payload, Class<P> clazz) throws MediaPlatformException {
        Request request;
        try {
            RequestBody body = RequestBody.create(
                    objectMapper.writeValueAsBytes(payload),
                    MEDIA_TYPE
            );
            request = defaultBuilder()
                    .post(body)
                    .url(url)
                    .build();
        } catch (JsonProcessingException e) {
            throw new MediaPlatformException(e.getMessage());
        }

        return doRequest(request, clazz);
    }

    public <P> P postForm(String url, String mimeType, byte[] content, Map<String, String> params, Class<P> clazz) throws MediaPlatformException {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "file-name",
                        RequestBody.create(content, MediaType.parse(mimeType))
                );

        params.forEach(bodyBuilder::addFormDataPart);

        Request request = defaultBuilder()
                .post(bodyBuilder.build())
                .url(url)
                .build();

        return doRequest(request, clazz);
    }

    public <P> P postForm(String url, String mimeType, File content, Map<String, String> params, Class<P> clazz) throws MediaPlatformException {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", content.getName(),
                        RequestBody.create(content, MediaType.parse(mimeType))
                );

        params.forEach(bodyBuilder::addFormDataPart);

        Request request = defaultBuilder()
                .post(bodyBuilder.build())
                .url(url)
                .build();

        return doRequest(request, clazz);
    }

    public <P> P put(String url, Object payload, @Nullable Map<String, String> params, Class<P> clazz) throws MediaPlatformException {
        HttpUrl withQuery = appendQuery(params, url);

        Request request;
        try {
            RequestBody body = RequestBody.create(
                    objectMapper.writeValueAsBytes(payload),
                    MEDIA_TYPE
            );
            request = defaultBuilder()
                    .put(body)
                    .url(withQuery)
                    .build();
        } catch (JsonProcessingException e) {
            throw new MediaPlatformException(e.getMessage());
        }

        return doRequest(request, clazz);
    }

    public <P> P delete(String url, @Nullable Map<String, String> params, Class<P> clazz) throws MediaPlatformException {
        HttpUrl withQuery = appendQuery(params, url);

        Request request = defaultBuilder()
                .delete()
                .url(withQuery)
                .build();

        return doRequest(request, clazz);
    }

    private Request.Builder defaultBuilder() {
        String authHeader = authenticator.getHeader();

        return new Request.Builder()
                .header("User-Agent", USER_AGENT)
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

    @Nullable
    private <P> P doRequest(Request request, Class<P> clazz) throws MediaPlatformException {
        // closable in try
        try (Response response = httpClient.newCall(request).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            if (e.getCause() instanceof MediaPlatformException) {
                throw (MediaPlatformException) e.getCause();
            }
            throw new MediaPlatformException(e.getMessage());
        }
    }

    @Nullable
    private <P> P handleResponse(Response response, @Nullable Class<P> clazz) throws MediaPlatformException {
        int statusCode = response.code();

        if (statusCode == 401) {
            throw new UnauthorizedException();
        }

        if (statusCode == 403) {
            throw new ForbiddenException();
        }

        if (statusCode == 404) {
            throw new ResourceNotFoundException(response.toString());
        }

        if (null == response.body()) {
            throw new MediaPlatformException("empty response", statusCode);
        }

        try {
            ResponseBody body = response.body();
            if (null == body) {
                return null;
            }

            if (clazz != null) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(RestResponse.class, clazz);
                RestResponse<P> restResponse = objectMapper.readValue(body.byteStream(), javaType);
                restResponse.throwOrReturn();
                return restResponse.getPayload();
            } else {
                RestResponse<?> restResponse = objectMapper.readValue(body.byteStream(), RestResponse.class);
                restResponse.throwOrReturn();
                return null;
            }
        } catch (IOException e) {
            throw new MediaPlatformException(e.getMessage());
        }
    }
}
