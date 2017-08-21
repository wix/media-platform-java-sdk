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
public class FileAclJsonSerializer implements JsonSerializer<FileDescriptor.Acl> {
    @Override
    public JsonElement serialize(FileDescriptor.Acl src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}
