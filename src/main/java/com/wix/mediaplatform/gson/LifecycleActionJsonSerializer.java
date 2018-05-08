package com.wix.mediaplatform.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wix.mediaplatform.dto.lifecycle.Lifecycle;

import java.lang.reflect.Type;

public class LifecycleActionJsonSerializer implements JsonSerializer<Lifecycle.Action> {
    @Override
    public JsonElement serialize(Lifecycle.Action action, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(action.getValue());
    }
}
