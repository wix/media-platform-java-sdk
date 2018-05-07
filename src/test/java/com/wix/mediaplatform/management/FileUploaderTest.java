package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.FileImportJob;
import com.wix.mediaplatform.dto.job.FileImportSpecification;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.lifecycle.Lifecycle;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ImportFileRequest;
import com.wix.mediaplatform.dto.response.GetUploadUrlResponse;
import com.wix.mediaplatform.dto.response.RestResponse;
import com.wix.mediaplatform.exception.FileAlreadyExistsException;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.wix.mediaplatform.dto.lifecycle.Lifecycle.Action.DELETE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileUploaderTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(configuration, AuthenticatedHTTPClient, gson);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getUploadUrl() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        GetUploadUrlResponse response = fileUploader.getUploadUrl(null);

        assertThat(response.getUploadToken(), is("some token"));
        assertThat(response.getUploadUrl(), is("https://localhost:8443/_api/upload/file"));
    }

    @Test
    public void uploadFile() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        FileDescriptor[] files = fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, null);

        assertThat(files[0].getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFileWithLifecycle() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-with-lifecycle-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        Lifecycle lifecycle = new Lifecycle().setAction(DELETE).setAge(100);
        FileDescriptor[] files = fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", new FileInputStream(file), null, lifecycle);

        assertThat(files[0].getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test
    public void uploadFilePrivate() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=private&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));
        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        FileDescriptor[] files = fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, "private");

        assertThat(files[0].getId(), is("c4516b12744b4ef08625f016a80aed3a"));
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void uploadFileAlreadyExists() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-already-exists-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, null);
    }

    @Test(expected = MediaPlatformException.class)
    public void uploadFileUnrecognizedError() throws Exception {
        stubFor(get(urlEqualTo("/_api/upload/url?acl=public&mimeType=text%2Fplain&path=%2Fa%2Fnew.txt"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-upload-url-response.json")));

        stubFor(post(urlEqualTo("/_api/upload/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("file-upload-unrecognized-error-response.json")));

        File file = new File(this.getClass().getClassLoader().getResource("source/image.jpg").getFile());
        fileUploader.uploadFile("/a/new.txt", "text/plain", "new.txt", file, null);
    }

    @Test
    public void importFilePending() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-pending-response.json")));

        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("http://source.url/filename.txt")
                .setDestination(new Destination()
                        .setAcl("public")
                        .setDirectory("/fish"));
        FileImportJob job = fileUploader.importFile(importFileRequest);
        FileImportSpecification specification = job.getSpecification();

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
        assertThat(job.getStatus(), is(Job.Status.pending.getValue()));
        assertThat(job.getType(), is(Job.Type.FILE_IMPORT.getValue()));
        assertThat(specification.getSourceUrl(), is("http://source.url/filename.txt"));
        assertThat(specification.getDestination().getAcl(), is("public"));
        assertThat(specification.getDestination().getDirectory(), is("/fish"));
    }

    @Test
    public void importFileSuccess() throws Exception {
        stubFor(post(urlEqualTo("/_api/import/file"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("import-file-success-response.json")));

        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("http://source.url/filename.ext")
                .setDestination(new Destination()
                        .setAcl("public")
                        .setDirectory("/fish"));
        FileImportJob job = fileUploader.importFile(importFileRequest);
        FileImportSpecification specification = job.getSpecification();
        RestResponse<FileDescriptor> result = job.getResult();

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
        assertThat(job.getStatus(), is(Job.Status.success.getValue()));
        assertThat(job.getType(), is(Job.Type.FILE_IMPORT.getValue()));

        assertThat(specification.getSourceUrl(), is("http://source.url/filename.txt"));
        assertThat(specification.getDestination().getAcl(), is("public"));
        assertThat(specification.getDestination().getDirectory(), is("/fish"));

        assertThat(result.getCode(), is(0));
        assertThat(result.getPayload().getId(), is("123"));
        assertThat(result.getPayload().getHash(), is("456"));
        assertThat(result.getPayload().getPath(), is("/fish/filename.txt"));
        assertThat(result.getPayload().getMimeType(), is("text/plain"));
        assertThat(result.getPayload().getType(), is(FileDescriptor.Type.FILE.getValue()));
        assertThat(result.getPayload().getSize(), is(100l));
        assertThat(result.getPayload().getAcl(), is(FileDescriptor.Acl.PUBLIC.getValue()));

    }
}
