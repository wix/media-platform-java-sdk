package com.wix.mediaplatform.e2e;

import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.exception.MediaPlatformException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.management.FileUploader;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by alonne on 17/10/2018.
 */
public class ParallelUploadsTest  extends BaseTest {
    private Configuration configuration = new Configuration("wixmp-ae48ec291ab9bd96e928fbda.appspot.com",
            "b7e59a50210d42f5a352dfe4488bb75a", "6b4409920c8767796824740dc65d3d7b");
    private Authenticator authenticator = new Authenticator(configuration);
    private com.wix.mediaplatform.http.AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private FileUploader fileUploader = new FileUploader(configuration, AuthenticatedHTTPClient, gson);
    private static final int numThreads = 20;
    private static final int numBatches = 3;

    public class UploadThread implements Runnable {
        public void run() {
            try {
                File file = new File(this.getClass().getClassLoader().getResource("source/svg.svg").getFile());
//                int length = (int)(13 * 1024 + (Math.random() * 10 * 1024));
//                String contents = randomAlphaNumeric(length);
//                IntStream stream = contents.chars();

                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString() + ".svg";

                FileDescriptor[] files = fileUploader.uploadFile("/users/c47dc6ff-56ad-438c-8b4a-276aabbe2e15/logos/ciu/monochrome-" + fileName, "image/svg+xml",
                        fileName, file, null);
                System.out.println("Uploaded successfully: threadId=" + Thread.currentThread().getId() + " fileName=" + fileName);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Test
    public void uploadParallelFiles() throws Exception {
        for (int i = 0; i < numBatches; i++) {
            System.out.println("Starting batch " + i);
            uploadBatch();
        }

    }

    private void uploadBatch() throws InterruptedException {
        Thread uploaderThreads[] = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            uploaderThreads[i] = new Thread(new UploadThread());
        }

        for (int i = 0; i < numThreads; i++) {
            uploaderThreads[i].start();
        }

        for (Thread thread: uploaderThreads) {
            thread.join();
        }
    }
}
