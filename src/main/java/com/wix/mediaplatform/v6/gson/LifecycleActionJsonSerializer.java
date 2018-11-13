package com.wix.mediaplatform.v6.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wix.mediaplatform.v6.service.FileLifecycle;

import java.lang.reflect.Type;

public class LifecycleActionJsonSerializer implements JsonSerializer<FileLifecycle.Action> {
    @Override
    public JsonElement serialize(FileLifecycle.Action action, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(action.getValue());
    }
}
