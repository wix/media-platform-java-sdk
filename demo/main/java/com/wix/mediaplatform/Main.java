package com.wix.mediaplatform;

import com.wix.mediaplatform.v6.MediaPlatform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final MediaPlatform mediaPlatform = new MediaPlatform(
            "wixmp-410a67650b2f46baa5d003c6.appspot.com",
            "48fa9aa3e9d342a3a33e66af08cd7fe3",
            "fad475d88786ab720b04f059ac674b0e"
    );

    public static void main(String[] args) throws Exception {

        for (String arg : args) {
            System.out.println(arg);
        }

        Demo demo = new Demo(mediaPlatform);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(() -> {
            try {
                demo.uploadImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                demo.importFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                demo.listJobs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//            demo.transcodeFile();
//            demo.createArchive();
//            demo.extractArchive();

        executor.shutdown();
    }
}
