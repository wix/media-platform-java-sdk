package com.wix.mediaplatform.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;

import java.lang.reflect.Type;

/**
 * Created by alonne on 19/07/2017.
 */
public class FileAclJsonDeserializer implements JsonDeserializer<FileDescriptor.Acl> {
    @Override
    public FileDescriptor.Acl deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return FileDescriptor.Acl.fromString(jsonElement.getAsString());
    }
}
