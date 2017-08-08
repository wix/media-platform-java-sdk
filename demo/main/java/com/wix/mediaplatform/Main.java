package com.wix.mediaplatform;

import com.wix.mediaplatform.exception.UnauthorizedException;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    private static final MediaPlatform mediaPlatform = new MediaPlatform(
            "wixmp-410a67650b2f46baa5d003c6.appspot.com",
            "48fa9aa3e9d342a3a33e66af08cd7fe3",
            "fad475d88786ab720b04f059ac674b0e");

    public static void main(String [] args) throws InterruptedException {

        for (String arg : args) {
            System.out.println(arg);
        }

        Demo demo = new Demo(mediaPlatform);

        try {
            demo.transcodeFile();
            demo.importFile();
            demo.uploadImage();
            demo.listJobs();
            demo.createArchive();
            demo.extractArchive();
        } catch (IOException | UnauthorizedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
