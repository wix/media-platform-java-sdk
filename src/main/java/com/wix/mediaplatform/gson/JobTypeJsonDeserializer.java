package com.wix.mediaplatform.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wix.mediaplatform.dto.job.Job;

import java.lang.reflect.Type;

/**
 * Created by alonne on 19/07/2017.
 */
public class JobTypeJsonDeserializer implements JsonDeserializer<Job.Type> {
    @Override
    public Job.Type deserialize(JsonElement jsonElement,
                                Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Job.Type.fromString(jsonElement.getAsString());
    }
}
