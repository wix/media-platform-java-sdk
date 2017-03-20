package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.UploadUrlRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.HTTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import static com.wix.mediaplatform.gson.Types.FILE_DESCRIPTORS_REST_RESPONSE;
import static com.wix.mediaplatform.gson.Types.GET_UPLOAD_URL_REST_RESPONSE;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUploader {

    private final HTTPClient HTTPClient;

    private final String uploadUrlEndpoint;

    public FileUploader(HTTPClient HTTPClient, Configuration configuration) {

        this.HTTPClient = HTTPClient;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(@Nullable UploadUrlRequest uploadUrlRequest) throws IOException, UnauthorizedException, URISyntaxException {
        Map<String, String> params = null;
        if (uploadUrlRequest != null) {
            params = uploadUrlRequest.toParams();
        }

        return HTTPClient.get(uploadUrlEndpoint, params, GET_UPLOAD_URL_REST_RESPONSE);
    }

    public FileDescriptor[] uploadFile(String path, String mimeType, String fileName, InputStream source, @Nullable String acl) throws IOException, UnauthorizedException, URISyntaxException {
        UploadUrlRequest uploadUrlRequest = new UploadUrlRequest()
                .setMimeType(mimeType)
                .setPath(path);
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(uploadUrlRequest);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setLaxMode();
        multipartEntityBuilder.setCharset(UTF_8);
        multipartEntityBuilder.addTextBody("path", path);
        multipartEntityBuilder.addTextBody("uploadToken", uploadUrlResponse.getUploadToken());
        multipartEntityBuilder.addTextBody("mimeType", mimeType);
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);
        if (acl != null) {
            multipartEntityBuilder.addTextBody("acl", acl);
        }

        HttpEntity form = multipartEntityBuilder.build();

        return HTTPClient.post(uploadUrlResponse.getUploadUrl(), form, FILE_DESCRIPTORS_REST_RESPONSE);
    }
}
