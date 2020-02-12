package com.wix.demousage;

import com.wix.mediaplatform.v7.MediaPlatform;
import com.wix.mediaplatform.v7.exception.MediaPlatformException;
import com.wix.mediaplatform.v7.image.Image;
import com.wix.mediaplatform.v7.service.Destination;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.file.ImportFileJob;
import com.wix.mediaplatform.v7.service.job.JobList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

class DemoOperations {

    private final MediaPlatform mediaPlatform;

    DemoOperations(MediaPlatform mediaPlatform) {
        this.mediaPlatform = mediaPlatform;
    }

    void importFile() throws MediaPlatformException {
        ImportFileJob importFileJob = mediaPlatform.fileManager().importFileRequest()
                .setSourceUrl("https://static.wixstatic.com/media/f31d7d0cfc554aacb1d737757c8d3f1b.jpg")
                .setDestination(new Destination()
                        .setDirectory("/demousage/import/" + UUID.randomUUID().toString())
                        .setAcl(FileDescriptor.Acl.PUBLIC))
                .execute();

        FileDescriptor imageFile = mediaPlatform.jobManager().awaitJob(importFileJob);

        String url = new Image(imageFile)
                .setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com")
                .crop(400, 300, 1, 2, 1)
                .toUrl();

        System.out.println("SEE IMPORTED IMAGE @ " + url);
    }

    @Deprecated
    void uploadImage() throws MediaPlatformException, IOException {
        String id = UUID.randomUUID().toString();

        Path localPath = new File(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("files/golan.jpg")
        ).getPath()).toPath();

        FileDescriptor file = mediaPlatform.fileManager().uploadFileRequest()
                .setPath("/demousage/upload/" + id + ".golan.jpg")
                .setMimeType("image/jpeg")
                .setContent(Files.readAllBytes(localPath))
                .execute();

        Image image = new Image(file).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");

        image.crop(200, 300, 0, 0, 2);
        System.out.println("SEE UPLOADED IMAGE @ " + image.toUrl());
        image.smartCrop(200, 300);
        System.out.println("SEE SMART CROPPED IMAGE @ " + image.toUrl());
    }

    void uploadImageV2() throws MediaPlatformException, IOException {
        String id = UUID.randomUUID().toString();

        Path localPath = new File(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("files/golan.jpg")
        ).getPath()).toPath();

        FileDescriptor file = mediaPlatform.fileManager().uploadFileRequestV2()
                .setPath("/demousage/upload/" + id + ".golan.jpg")
                .setMimeType("image/jpeg")
                .setContent(Files.readAllBytes(localPath))
                .execute();

        Image image = new Image(file).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");

        image.crop(200, 300, 0, 0, 2);
        System.out.println("SEE UPLOADED IMAGE @ " + image.toUrl());
    }

    void uploadImageV2FromFile() throws MediaPlatformException, IOException {
        String id = UUID.randomUUID().toString();

        File localFile = new File(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("files/golan.jpg")
        ).getPath());

        FileDescriptor file = mediaPlatform.fileManager().uploadFileRequestV2()
                .setPath("/demousage/upload/" + id + ".golan.jpg")
                .setMimeType("image/jpeg")
                .setFile(localFile)
                .execute();

        Image image = new Image(file).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");

        image.crop(200, 300, 0, 0, 2);
        System.out.println("SEE UPLOADED IMAGE @ " + image.toUrl());
    }

    void listJobs() throws MediaPlatformException {
        JobList jobList = mediaPlatform.jobManager().jobListRequest()
                .setType("urn:job:import.file")
                .setPageSize(3)
                .execute();

        System.out.println("JOB LIST: " + jobList.toString());
    }
}
