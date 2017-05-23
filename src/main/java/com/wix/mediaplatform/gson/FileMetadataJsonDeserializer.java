package com.wix.mediaplatform.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.metadata.basic.BasicMetadata;
import com.wix.mediaplatform.dto.metadata.basic.ImageBasicMetadata;
import com.wix.mediaplatform.dto.metadata.basic.VideoBasicMetadata;
import com.wix.mediaplatform.dto.metadata.features.Features;
import com.wix.mediaplatform.dto.metadata.features.ImageFeatures;

import java.lang.reflect.Type;

public class FileMetadataJsonDeserializer implements JsonDeserializer<FileMetadata> {

    @Override
    public FileMetadata deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FileDescriptor fileDescriptor = context.deserialize(
                json.getAsJsonObject().getAsJsonObject("fileDescriptor"),
                FileDescriptor.class);
        BasicMetadata basic = null;
        Features features = null;
        String type = fileDescriptor.getMimeType().split("/")[0];
        switch (type) {
            case "image":
                basic = context.deserialize(json.getAsJsonObject().getAsJsonObject("basic"), ImageBasicMetadata.class);
                features = context.deserialize(json.getAsJsonObject().getAsJsonObject("features"), ImageFeatures.class);
                break;
            case "video":
                basic = context.deserialize(json.getAsJsonObject().getAsJsonObject("basic"), VideoBasicMetadata.class);
                break;
        }

        return new FileMetadata(fileDescriptor, basic, features);
    }
}
