package com.wix.mediaplatform.management;

import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.request.Attachment;
import com.wix.mediaplatform.dto.request.DownloadUrlRequest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

public class FileDownloaderTest extends BaseTest {

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private FileDownloader fileDownloader = new FileDownloader(configuration, authenticator);

    @Test
    public void getDownloadUrlDefault() throws Exception {
        String url = fileDownloader.getDownloadUrl("/file.txt");

        assertThat(url, startsWith("https://localhost:8443/_api/download/file?downloadToken="));
    }

    @Test
    public void getDownloadUrlWithOptions() throws Exception {
        DownloadUrlRequest downloadUrlRequest = new DownloadUrlRequest()
                .setOnExpireRedirectTo("url")
                .setAttachment(new Attachment()
                        .setFilename("fish"));
        String url = fileDownloader.getDownloadUrl("/file.txt", downloadUrlRequest);

        assertThat(url, startsWith("https://localhost:8443/_api/download/file?downloadToken="));
    }
}