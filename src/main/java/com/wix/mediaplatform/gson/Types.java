package com.wix.mediaplatform.gson;

import com.google.gson.reflect.TypeToken;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.ListFilesResponse;
import com.wix.mediaplatform.dto.response.RestResponse;

import java.lang.reflect.Type;

public class Types {
    public static final Type FILE_DESCRIPTOR_REST_RESPONSE = new TypeToken<RestResponse<FileDescriptor>>(){}.getType();
    public static final Type FILE_DESCRIPTORS_REST_RESPONSE = new TypeToken<RestResponse<FileDescriptor[]>>(){}.getType();
    public static final Type FILE_LIST_REST_RESPONSE = new TypeToken<RestResponse<ListFilesResponse>>(){}.getType();
    public static final Type GET_UPLOAD_URL_REST_RESPONSE = new TypeToken<RestResponse<GetUploadUrlResponse>>(){}.getType();
}
