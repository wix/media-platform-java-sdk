package com.wix.mediaplatform.dto.upload;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.MediaType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ImportFileRequest extends UploadRequest {

    private String url;

    @SerializedName("media_type")
    private MediaType mediaType;

    private String name;

    public ImportFileRequest() {
    }

    public ImportFileRequest(String url, MediaType mediaType, Set<String> tags, @Nullable String name, @Nullable String parentFolderId) {
        super(tags, parentFolderId);
        this.url = url;
        this.mediaType = mediaType;
        this.name = name;
    }

    public ImportFileRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public ImportFileRequest setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public ImportFileRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    @Nullable
    public String getName() {
        return name;
    }
}
