package com.wix.mediaplatform.v6.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.v6.service.FileLifecycle;

import java.lang.reflect.Type;

public class LifecycleActionJsonDeserializer implements JsonDeserializer<FileLifecycle.Action> {
    @Override
    public FileLifecycle.Action deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return FileLifecycle.Action.fromString(jsonElement.getAsString());
    }
}
