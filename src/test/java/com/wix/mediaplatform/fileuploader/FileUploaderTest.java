package com.wix.mediaplatform.fileuploader;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.audio.AudioDTO;
import com.wix.mediaplatform.dto.document.DocumentDTO;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.dto.upload.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.upload.UploadRequest;
import com.wix.mediaplatform.dto.video.EncodingOptions;
import com.wix.mediaplatform.dto.video.VideoDTO;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileUploaderTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(authenticatedHTTPClient, gson, configuration);

    private UploadRequest uploadRequest = new UploadRequest(newHashSet("fish", "cat"), "folderId");

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getUploadUrl() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        GetUploadUrlResponse response = fileUploader.getUploadUrl("userId");

        assertThat(response.getUploadToken(), is("token"));
        assertThat(response.getUploadUrl(), is("url"));
    }

    @Test
    public void uploadImageFromFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        ImageDTO imageDTO = fileUploader.uploadImage("userId", file, null);

        assertThat(imageDTO.getHeight(), is(17));
    }

    @Test
    public void uploadImageFromFileWithUploadRequest() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("image-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        ImageDTO imageDTO = fileUploader.uploadImage("userId", file, uploadRequest);

        assertThat(imageDTO.getHeight(), is(17));
    }

    @Test
    public void uploadAudioFromFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("audio-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/audio.mp3").getFile());
        AudioDTO audioDTO = fileUploader.uploadAudio("userId", file, null);

        assertThat(audioDTO.getFileSize(), is(3528120L));
    }

    @Test
    public void uploadAudioFromFileWithUploadRequest() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("audio-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/audio.mp3").getFile());
        AudioDTO audioDTO = fileUploader.uploadAudio("userId", file, uploadRequest);

        assertThat(audioDTO.getFileSize(), is(3528120L));
    }

    @Test
    public void uploadVideoFromFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("video-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/video.mp4").getFile());
        VideoDTO videoDTO = fileUploader.uploadVideo("userId", file, null, null);

        assertThat(videoDTO.getFileName(), is("e66d82_ca6c7b4fc81f45c9bcf219d81395d3ec"));
    }

    @Test
    public void uploadVideoFromFileWithUploadRequest() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("video-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/video.mp4").getFile());
        VideoDTO videoDTO = fileUploader.uploadVideo("userId", file, uploadRequest, null);

        assertThat(videoDTO.getDateCreated(), is(1466344754L));
    }

    @Test
    public void uploadVideoFromFileWithUploadRequestAndEncodingOptions() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("video-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/video.mp4").getFile());
        VideoDTO videoDTO = fileUploader.uploadVideo("userId", file, uploadRequest,
                new EncodingOptions(newHashSet(EncodingOptions.VideoFormat.MP4),
                        true,
                        true,
                        EncodingOptions.AudioFormat.M4A,
                        EncodingOptions.ImageFormat.JPEG));

        assertThat(videoDTO.getDateModified(), is(1466344753L));
    }

    @Test
    public void uploadDocumentFromFile() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("document-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/document.xlsx").getFile());
        DocumentDTO documentDTO = fileUploader.uploadDocument("userId", file, null);

        assertThat(documentDTO.getFileUrl(), is("ggl-109789773458215503884/audio/af63a5d465ce48a998297684f3246df6/file.mp3"));
    }

    @Test
    public void uploadDocumentFromFileWithUploadRequest() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");

        stubFor(get(urlEqualTo("/files/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(new GetUploadUrlResponse("https://" + configuration.getDomain() + "/upload", "token")))));
        stubFor(post(urlEqualTo("/upload"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("document-upload-response.json")));

        @SuppressWarnings("ConstantConditions")
        File file = new File(this.getClass().getClassLoader().getResource("source/document.xlsx").getFile());
        DocumentDTO documentDTO = fileUploader.uploadDocument("userId", file, uploadRequest);

        assertThat(documentDTO.getIconUrl(), is("wixmedia-public/images/b0068f926fc542fbb1f3653df8ce5099/music_note.png"));
    }
}
