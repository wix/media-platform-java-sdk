package com.wix.mediaplatform.fileuploader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.FileDTO;
import com.wix.mediaplatform.dto.MediaType;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.collection.ResponseWrapper;
import com.wix.mediaplatform.dto.document.DocumentDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.upload.ImportFileRequest;
import com.wix.mediaplatform.dto.upload.UploadRequest;
import com.wix.mediaplatform.dto.video.EncodingOptions;
import com.wix.mediaplatform.dto.video.VideoDTO;
import com.wix.mediaplatform.exception.FileSizeException;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUploader {

    private final Type fileDTOWrappedResponseType = new TypeToken<ResponseWrapper<FileDTO>>() {}.getType();

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final Gson gson;
    private final String baseUrl;
    private final String uploadUrlEndpoint;

    public FileUploader(AuthenticatedHTTPClient authenticatedHTTPClient, Gson gson, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;
        this.gson = gson;

        this.baseUrl = "https://" + configuration.getDomain() + "/files/upload";
        this.uploadUrlEndpoint = "https://" + configuration.getDomain() + "/files/upload/url";
    }

    public GetUploadUrlResponse getUploadUrl(String userId) throws IOException, UnauthorizedException, URISyntaxException {
        return authenticatedHTTPClient.get(userId, uploadUrlEndpoint, null, GetUploadUrlResponse.class);
    }

    public ImageDTO uploadImage(String userId, File image, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        String fileName = image.getName();
        String mimeType = Files.probeContentType(image.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        InputStream source = new FileInputStream(image);

        return uploadImage(userId, source, mimeType, fileName, uploadRequest);
    }

    public ImageDTO uploadImage(String userId, InputStream image, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        ImageDTO[] files = upload(userId, MediaType.IMAGE, mimeType, fileName, image, uploadRequest, null, ImageDTO[].class);

        return files[0];
    }

    public AudioDTO uploadAudio(String userId, File audio, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        String fileName = audio.getName();
        String mimeType = Files.probeContentType(audio.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        InputStream source = new FileInputStream(audio);

        return uploadAudio(userId, source, mimeType, fileName, uploadRequest);
    }

    public AudioDTO uploadAudio(String userId, InputStream audio, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        AudioDTO[] files = upload(userId, MediaType.AUDIO, mimeType, fileName, audio, uploadRequest, null, AudioDTO[].class);

        return files[0];
    }

    public VideoDTO uploadVideo(String userId, File video, @Nullable UploadRequest uploadRequest, @Nullable EncodingOptions encodingOptions) throws UnauthorizedException, IOException, URISyntaxException {
        String fileName = video.getName();
        String mimeType = Files.probeContentType(video.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        InputStream source = new FileInputStream(video);

        return uploadVideo(userId, source, mimeType, fileName, uploadRequest, encodingOptions);
    }

    public VideoDTO uploadVideo(String userId, InputStream video, String mimeType, String fileName, @Nullable UploadRequest uploadRequest, @Nullable EncodingOptions encodingOptions) throws UnauthorizedException, IOException, URISyntaxException {
        Map<String, String> additionalParams = newHashMap();
        if (encodingOptions !=  null) {
            additionalParams.put("encoding_options", gson.toJson(encodingOptions.toJSON()));
        }

        VideoDTO[] files = upload(userId, MediaType.VIDEO, mimeType, fileName, video, uploadRequest, additionalParams, VideoDTO[].class);

        return files[0];
    }

    public DocumentDTO uploadDocument(String userId, File document, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        String fileName = document.getName();
        String mimeType = Files.probeContentType(document.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        InputStream source = new FileInputStream(document);

        return uploadDocument(userId, source, mimeType, fileName, uploadRequest);
    }

    public DocumentDTO uploadDocument(String userId, InputStream document, String mimeType, String fileName, @Nullable UploadRequest uploadRequest) throws UnauthorizedException, IOException, URISyntaxException {
        DocumentDTO[] files = upload(userId, MediaType.DOCUMENT, mimeType, fileName, document, uploadRequest, null, DocumentDTO[].class);

        return files[0];
    }

    public FileDTO importFile(String userId, ImportFileRequest importFileRequest) throws UnauthorizedException, IOException, URISyntaxException {
        ResponseWrapper<FileDTO> response = authenticatedHTTPClient.postWithSelfSignedToken(userId, baseUrl + "/external/async", importFileRequest, null, fileDTOWrappedResponseType);

        if (response.getCode() == -7752) {
            throw new FileSizeException();
        }

        if (response.getCode() != 0) {
            throw new RuntimeException(String.valueOf(response.getCode()));
        }
        //noinspection ConstantConditions
        return response.getPayload();
    }

    public <T> T upload(String userId, MediaType mediaType, String mimeType, String fileName, InputStream source, @Nullable UploadRequest uploadRequest, @Nullable Map<String, String> additionalParams, Type responseType) throws IOException, UnauthorizedException, URISyntaxException {
        GetUploadUrlResponse uploadUrlResponse = getUploadUrl(userId);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setLaxMode();
        multipartEntityBuilder.setCharset(UTF_8);
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
