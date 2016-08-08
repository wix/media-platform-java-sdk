package com.wix.mediaplatform.fileuploader;

import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileUploaderTest extends BaseTest {

    private AuthenticatedHTTPClient authenticatedHTTPClient = mock(AuthenticatedHTTPClient.class);
    private Configuration configuration = new Configuration("domain.test", "appId", "sharedSecret");
    private FileUploader fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);

    @Test
    public void getUploadUrl() throws Exception {

        GetUploadUrlResponse uploadUrlResponse = new GetUploadUrlResponse("url", "token");
        when(authenticatedHTTPClient.get("userId", "https://domain.test/files/upload/url", null, GetUploadUrlResponse.class))
                .thenReturn(uploadUrlResponse);

        GetUploadUrlResponse response = fileUploader.getUploadUrl("userId");

        assertThat(response, is(uploadUrlResponse));
    }
}
