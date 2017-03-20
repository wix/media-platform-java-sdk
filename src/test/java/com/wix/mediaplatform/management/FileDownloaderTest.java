package com.wix.mediaplatform.management;

import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileDownloaderTest extends BaseTest {

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private FileDownloader fileDownloader = new FileDownloader(configuration, authenticator);

    @Test
    public void getDownloadUrlDefault() throws Exception {
        String url = fileDownloader.getDownloadUrl("/file.txt");

        assertThat(url, is("/path_1"));
    }
}