package com.wix.mediaplatform.fileuploader;

import com.google.gson.Gson;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.MediaType;
import com.wix.mediaplatform.dto.VideoDTO.EncodingOptions;
import com.wix.mediaplatform.dto.VideoDTO.VideoDTO;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.document.DocumentDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.upload.UploadRequest;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileUploader {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final Gson gson;
    private final String uploadUrlEndpoint;

    public FileUploader(AuthenticatedHTTPClient authenticatedHTTPClient, Gson gson, Configuration configuration) {

        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.gson = gson;

        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(String userId) throws IOException, UnauthorizedException, URISyntaxException {
        return authenticatedHTTPClient.get(userId, uploadUrlEndpoint, null, GetUploadUrlResponse.class);
    }

    public ImageDTO uploadImage(String userId, InputStream image, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return upload(userId, MediaType.IMAGE, mimeType, fileName, image, uploadRequest, null, ImageDTO.class);
    }

    public AudioDTO uploadAudio(String userId, InputStream audio, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return upload(userId, MediaType.AUDIO, mimeType, fileName, audio, uploadRequest, null, AudioDTO.class);
    }

    public VideoDTO uploadVideo(String userId, InputStream video, String mimeType, String fileName, @Nullable UploadRequest uploadRequest, @Nullable EncodingOptions encodingOptions) throws UnauthorizedException, IOException, URISyntaxException {

        Map<String, String> additionalParams = newHashMap();
        if (encodingOptions !=  null) {
            additionalParams.put("encoding_options", gson.toJson(encodingOptions.toJSON()));
        }

        return upload(userId, MediaType.VIDEO, mimeType, fileName, video, uploadRequest, additionalParams, VideoDTO.class);
    }

    public DocumentDTO uploadDocument(String userId, InputStream document, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return upload(userId, MediaType.DOCUMENT, mimeType, fileName, document, uploadRequest, null, DocumentDTO.class);
    }

    private <T> T upload(String userId, MediaType mediaType, String mimeType, String fileName, InputStream source, @Nullable UploadRequest uploadRequest, @Nullable Map<String, String> additionalParams, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {

        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(userId);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file", source, ContentType.parse(mimeType), fileName);
        multipartEntityBuilder.addTextBody("media_type", mediaType.getMediaType());
        multipartEntityBuilder.addTextBody("upload_token", uploadUrlResponse.getUploadToken());
        if (uploadRequest != null) {
            if (uploadRequest.getParentFolderId() != null) {
                multipartEntityBuilder.addTextBody("parent_folder_id", uploadRequest.getParentFolderId());
            }
            if (uploadRequest.getTags() != null && !uploadRequest.getTags().isEmpty()) {
                multipartEntityBuilder.addTextBody("tags", StringUtils.join(uploadRequest.getTags(), ","));
            }
        }
        if (additionalParams != null) {
            for (Map.Entry<String, String> entry : additionalParams.entrySet()) {
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity form = multipartEntityBuilder.build();

        return authenticatedHTTPClient.postMultipartAnonymous(uploadUrlResponse.getUploadUrl(), form, responseType);
    }
}
