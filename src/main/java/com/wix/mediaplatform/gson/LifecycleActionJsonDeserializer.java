package com.wix.mediaplatform.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.dto.lifecycle.Lifecycle;

import java.lang.reflect.Type;

public class LifecycleActionJsonDeserializer implements JsonDeserializer<Lifecycle.Action> {
    @Override
    public Lifecycle.Action deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Lifecycle.Action.fromString(jsonElement.getAsString());
    }
}
