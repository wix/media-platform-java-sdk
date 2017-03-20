package com.wix.mediaplatform.management;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.UploadUrlRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.wix.mediaplatform.gson.Types.FILE_DESCRIPTORS_REST_RESPONSE;
import static com.wix.mediaplatform.gson.Types.GET_UPLOAD_URL_REST_RESPONSE;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUploader {

    private final AuthenticatedHTTPClient AuthenticatedHTTPClient;

    private final String uploadUrlEndpoint;

    public FileUploader(Configuration configuration, AuthenticatedHTTPClient AuthenticatedHTTPClient) {

        this.AuthenticatedHTTPClient = AuthenticatedHTTPClient;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(@Nullable UploadUrlRequest uploadUrlRequest) throws IOException, UnauthorizedException, URISyntaxException {
        Map<String, String> params = null;
        if (uploadUrlRequest != null) {
            params = uploadUrlRequest.toParams();
        }

        return AuthenticatedHTTPClient.get(uploadUrlEndpoint, params, GET_UPLOAD_URL_REST_RESPONSE);
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, File source, @Nullable String acl) throws IOException, UnauthorizedException, URISyntaxException {
        UploadUrlRequest uploadUrlRequest = new UploadUrlRequest()
                .setMimeType(mimeType)
                .setPath(path);
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(uploadUrlRequest);

        MultipartEntityBuilder multipartEntityBuilder = prepareForm(path, mimeType, acl, uploadUrlResponse);
        multipartEntityBuilder.addBinaryBody(fileName, source);

        HttpEntity form = multipartEntityBuilder.build();

        return AuthenticatedHTTPClient.post(uploadUrlResponse.getUploadUrl(), form, FILE_DESCRIPTORS_REST_RESPONSE);
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, InputStream source, @Nullable String acl) throws IOException, UnauthorizedException, URISyntaxException {
        UploadUrlRequest uploadUrlRequest = new UploadUrlRequest()
                .setMimeType(mimeType)
                .setPath(path);
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(uploadUrlRequest);

        MultipartEntityBuilder multipartEntityBuilder = prepareForm(path, mimeType, acl, uploadUrlResponse);
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);

        HttpEntity form = multipartEntityBuilder.build();

        return AuthenticatedHTTPClient.post(uploadUrlResponse.getUploadUrl(), form, FILE_DESCRIPTORS_REST_RESPONSE);
    }

    private MultipartEntityBuilder prepareForm(String path, String mimeType, @Nullable String acl, GetUploadUrlResponse uploadUrlResponse) {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setLaxMode();
        multipartEntityBuilder.setCharset(UTF_8);
        multipartEntityBuilder.addTextBody("path", path);
        multipartEntityBuilder.addTextBody("uploadToken", uploadUrlResponse.getUploadToken());
        multipartEntityBuilder.addTextBody("mimeType", mimeType);
        if (acl != null) {
            multipartEntityBuilder.addTextBody("acl", acl);
        }
        return multipartEntityBuilder;
    }
}
