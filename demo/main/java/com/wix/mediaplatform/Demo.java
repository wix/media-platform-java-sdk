package com.wix.mediaplatform;

import com.wix.mediaplatform.v6.MediaPlatform;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import com.wix.mediaplatform.v6.image.Image;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.file.ImportFileJob;
import com.wix.mediaplatform.v6.service.job.JobList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

class Demo {

    private final MediaPlatform mediaPlatform;

    Demo(MediaPlatform mediaPlatform) {
        this.mediaPlatform = mediaPlatform;
    }

    void importFile() throws MediaPlatformException {

        ImportFileJob importFileJob = mediaPlatform.fileManager().importFileRequest()
                .setSourceUrl("https://static.wixstatic.com/media/f31d7d0cfc554aacb1d737757c8d3f1b.jpg")
                .setDestination(new Destination()
                        .setDirectory("/demo/import/" + UUID.randomUUID().toString())
                        .setAcl(FileDescriptor.Acl.PUBLIC))
                .execute();

        FileDescriptor imageFile = mediaPlatform.jobManager().awaitJob(importFileJob);

        String url = new Image(imageFile)
                .setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com")
                .smartCrop(400, 300)
                .toUrl();

        System.out.println("SEE IMPORTED IMAGE @ " + url);
    }

    void uploadImage() throws MediaPlatformException, IOException {

        String id = UUID.randomUUID().toString();

        Path localPath = new File(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("files/golan.jpg")
        ).getPath()).toPath();

        FileDescriptor file = mediaPlatform.fileManager().uploadFileRequest()
                .setPath("/demo/upload/" + id + ".golan.jpg")
                .setMimeType("image/jpeg")
                .setContent(Files.readAllBytes(localPath))
                .execute();

        Image image = new Image(file).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");

        image.crop(200, 300, 0, 0, 2);
        System.out.println("SEE UPLOADED IMAGE @ " + image.toUrl());
        image.smartCrop(200, 300);
        System.out.println("SEE SMART CROPPED IMAGE @ " + image.toUrl());
    }

    void listJobs() throws MediaPlatformException {
        JobList jobList = mediaPlatform.jobManager().jobListRequest()
                .setType("urn:job:import.file")
                .setPageSize(3)
                .execute();

        System.out.println("JOB LIST: " + jobList.toString());
    }
}
