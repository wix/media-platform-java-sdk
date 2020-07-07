package com.wix.demousage;

import com.wix.mediaplatform.v8.MediaPlatform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final MediaPlatform mediaPlatform = new MediaPlatform(
            "wixmp-410a67650b2f46baa5d003c6.appspot.com",
            "48fa9aa3e9d342a3a33e66af08cd7fe3",
            "fad475d88786ab720b04f059ac674b0e"
    );

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }

        DemoOperations demoOperations = new DemoOperations(mediaPlatform);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(() -> {
            try {
                demoOperations.uploadImageFromFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                demoOperations.imageWithWatermark();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                demoOperations.importFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                demoOperations.listJobs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//            demoOperations.transcodeFile();
//            demoOperations.createArchive();
//            demoOperations.extractArchive();

        executor.shutdown();
    }
}
