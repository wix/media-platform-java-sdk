package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.management.ListFilesResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

    private FileManager fileManager = new FileManager(authenticatedHTTPClient, configuration);

    @Test
    public void listFiles() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/files/getpage"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        ListFilesResponse response = fileManager.listFiles("userId", null);

        System.out.println(response);

        assertThat(response.getCount(), is(2));
    }

    @Test
    public void getFile() throws Exception {

    }

    @Test
    public void updateFile() throws Exception {

    }

    @Test
    public void deleteFile() throws Exception {

    }

    @Test
    public void listFolders() throws Exception {

    }

    @Test
    public void newFolder() throws Exception {

    }

    @Test
    public void updateFolder() throws Exception {

    }

    @Test
    public void deleteFolder() throws Exception {

    }
}