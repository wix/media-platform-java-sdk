package com.wix.mediaplatform.gson;

import com.google.gson.reflect.TypeToken;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.dto.response.*;

import java.lang.reflect.Type;

public class Types {
    public static final Type FILE_DESCRIPTOR_REST_RESPONSE = new TypeToken<RestResponse<FileDescriptor>>(){}.getType();
    public static final Type FILE_DESCRIPTORS_REST_RESPONSE = new TypeToken<RestResponse<FileDescriptor[]>>(){}.getType();
    public static final Type FILE_LIST_REST_RESPONSE = new TypeToken<RestResponse<ListFilesResponse>>(){}.getType();
    public static final Type GET_UPLOAD_URL_REST_RESPONSE = new TypeToken<RestResponse<GetUploadUrlResponse>>(){}.getType();
    public static final Type FILE_METADATA_REST_RESPONSE = new TypeToken<RestResponse<FileMetadata>>(){}.getType();
    public static final Type JOB_REST_RESPONSE = new TypeToken<RestResponse<Job>>(){}.getType();
    public static final Type JOBS_REST_RESPONSE = new TypeToken<RestResponse<Job[]>>(){}.getType();
    public static final Type SEARCH_JOBS_REST_RESPONSE = new TypeToken<RestResponse<SearchJobsResponse>>(){}.getType();
    public static final Type TRANSCODE_JOBS_REST_RESPONSE = new TypeToken<RestResponse<TranscodeJobsResponse>>(){}.getType();
}
