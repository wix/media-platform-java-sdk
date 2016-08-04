package com.wix.mediaplatform.http;

import okhttp3.CacheControl;

public class Constants {

    public static final CacheControl DO_NOT_CACHE = new CacheControl.Builder()
            .noCache()
            .noStore()
            .build();

    public static final String AUTHORIZATION_HEADER = "Authorization";
}
