package com.wix.mediaplatform.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;

import java.lang.reflect.Type;

/**
 * Created by alonne on 19/07/2017.
 */
public class FileTypeJsonSerializer implements JsonSerializer<FileDescriptor.Type> {
    @Override
    public JsonElement serialize(FileDescriptor.Type src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}
