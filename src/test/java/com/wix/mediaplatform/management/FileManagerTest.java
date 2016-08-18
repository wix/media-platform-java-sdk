package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.MediaType;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.folder.FolderDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.management.*;
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

        assertThat(response.getCount(), is(2));
    }

    @Test
    public void listFilesWithOptions() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/files/getpage?cursor=cursor&page_size=10&order=-date&parent_folder_id=parentFolderId&media_type=video&tag=tag"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-files-response.json")));

        ListFilesResponse response = fileManager.listFiles("userId", new ListFilesRequest()
                .setOrder(ListFilesRequest.OrderBy.date)
                .descending()
                .setCursor("cursor")
                .setSize(10)
                .setMediaType(MediaType.VIDEO)
                .setParentFolderId("parentFolderId")
                .setTag("tag"));

        assertThat(response.getCount(), is(2));
    }

    @Test
    public void getFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-file-image-response.json")));

        ImageDTO file = (ImageDTO) fileManager.getFile("userId", "fileId");

        assertThat(file.getAnalysis().getFaces().toArray(new ImageDTO.Square[1])[0].getHeight(), is(207));
    }

    @Test
    public void updateFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(put(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("update-file-audio-response.json")));

        AudioDTO file = (AudioDTO) fileManager.updateFile("userId", "fileId", new UpdateFileRequest()
                .setOriginalFileName("file")
                .setParentFolderId("parent")
                .addTag("tag"));

        assertThat(file.getAnalysis().getBitrate(), is(48000));
    }

    @Test
    public void deleteFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(delete(urlEqualTo("/files/fileId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        fileManager.deleteFile("userId", "fileId");
    }

    @Test
    public void listFolders() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/folders"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-folders-response.json")));

        ListFoldersResponse response = fileManager.listFolders("userId", null);

        assertThat(response.getFolders().toArray(new FolderDTO[3])[0].getFolderName(), is("fish"));
    }

    @Test
    public void listFoldersWithParentFolderId() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/folders/parentFolderId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-folders-response.json")));

        ListFoldersResponse response = fileManager.listFolders("userId", "parentFolderId");

        assertThat(response.getFolders().toArray(new FolderDTO[3])[0].getFolderName(), is("fish"));
    }

    @Test
    public void newFolder() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/folders"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("folder-dto-response.json")));

        FolderDTO folder = fileManager.newFolder("userId", new NewFolderRequest()
                .setFolderName("name")
                .setParentFolderId("parent")
                .setMediaType(MediaType.DOCUMENT)
        );

        assertThat(folder.getFolderName(), is("cat fish"));
    }

    @Test
    public void updateFolder() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(put(urlEqualTo("/folders/folderId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("folder-dto-response.json")));

        FolderDTO folder = fileManager.updateFolder("userId", "folderId", new UpdateFolderRequest()
                .setFolderName("name")
        );

        assertThat(folder.getFolderName(), is("cat fish"));
    }

    @Test
    public void deleteFolder() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(delete(urlEqualTo("/folders/folderId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        fileManager.deleteFolder("userId", "folderId");
    }
}