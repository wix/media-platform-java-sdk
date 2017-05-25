package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.FileImportJob;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ImportFileRequest;
import com.wix.mediaplatform.dto.request.SearchJobsRequest;
import com.wix.mediaplatform.dto.response.SearchJobsResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

class Demo {

    private final MediaPlatform mediaPlatform;

    private final Gson gson = MediaPlatform.getGson();

    Demo(MediaPlatform mediaPlatform) {
        this.mediaPlatform = mediaPlatform;
    }

    void importFile() throws IOException, UnauthorizedException, URISyntaxException {
        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("https://images1.ynet.co.il/PicServer5/2017/05/23/7800232/7800186320180autoresize.jpg")
                .setDestination(new Destination()
                        .setDirectory("/" + UUID.randomUUID().toString())
                        .setAcl("public"));
        Job job = mediaPlatform.fileManager().importFile(importFileRequest);

        System.out.println(gson.toJson(job));
    }

    void uploadImage() throws IOException, UnauthorizedException, URISyntaxException {

        String id = UUID.randomUUID().toString();

        File file = new File(this.getClass().getClassLoader().getResource("files/golan.jpg").getFile());
        FileDescriptor[] files = mediaPlatform.fileManager().uploadFile("/demo/" + id + ".golan.jpg","image/jpeg", "golan.jpg", file, null);
        Image image = new Image(files[0]).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");
        image.crop(200, 300, 0, 0, 2);
        System.out.println(image.toUrl());
        image.smartCrop(200, 300);
        System.out.println(image.toUrl());
    }

    void listJobs() throws IOException, UnauthorizedException, URISyntaxException {
        SearchJobsResponse response = mediaPlatform.jobManager().searchJobs(new SearchJobsRequest()
                .setType(FileImportJob.job_type));

        System.out.println(gson.toJson(response));
    }
}
