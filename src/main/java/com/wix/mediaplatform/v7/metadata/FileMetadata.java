package com.wix.mediaplatform.v7.metadata;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wix.mediaplatform.v7.metadata.basic.BasicMetadata;
import com.wix.mediaplatform.v7.metadata.basic.ImageBasicMetadata;
import com.wix.mediaplatform.v7.metadata.basic.VideoBasicMetadata;
import com.wix.mediaplatform.v7.metadata.features.Features;
import com.wix.mediaplatform.v7.metadata.features.ImageFeatures;
import com.wix.mediaplatform.v7.service.FileDescriptor;


public class FileMetadata {

    private String mediaType;

    private FileDescriptor fileDescriptor;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
            property = "mediaType",
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = VideoBasicMetadata.class, name = "video"),
            @JsonSubTypes.Type(value = ImageBasicMetadata.class, name = "image"),
//        @JsonSubTypes.Type(value = AudioBasicMetadata.class, name = "audio"), // todo
    })
    private BasicMetadata basic;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
            property = "mediaType",
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY
    )
    @JsonSubTypes({
//            @JsonSubTypes.Type(value = VideoFeatures.class, name = "video"),
            @JsonSubTypes.Type(value = ImageFeatures.class, name = "image"),
    })
    private Features features;

    public FileMetadata() {
    }

    public String getMediaType() {
        return mediaType;
    }

    public FileMetadata setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FileDescriptor getFileDescriptor() {
        return fileDescriptor;
    }

    public FileMetadata setFileDescriptor(FileDescriptor fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
        return this;
    }

    public BasicMetadata getBasic() {
        return basic;
    }

    public FileMetadata setBasic(BasicMetadata basic) {
        this.basic = basic;
        return this;
    }

    public Features getFeatures() {
        return features;
    }

    public FileMetadata setFeatures(Features features) {
        this.features = features;
        return this;
    }
}
