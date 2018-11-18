package com.wix.mediaplatform.v6.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.v6.metadata.FileMetadata;
import com.wix.mediaplatform.v6.metadata.basic.BasicMetadata;
import com.wix.mediaplatform.v6.metadata.basic.ImageBasicMetadata;
import com.wix.mediaplatform.v6.metadata.basic.VideoBasicMetadata;
import com.wix.mediaplatform.v6.metadata.features.Features;
import com.wix.mediaplatform.v6.metadata.features.ImageFeatures;
import com.wix.mediaplatform.v6.service.FileDescriptor;

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
