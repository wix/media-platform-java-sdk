package com.wix.mediaplatform.dto.VideoDTO;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;

public class EncodingOptions {

    private Set<VideoFormat> videoFormats;

    private boolean skipAudio;

    private boolean extractAudio;

    private AudioFormat audioFormat;

    private ImageFormat imageFormat;

    public EncodingOptions() {
    }

    public Set<VideoFormat> getVideoFormats() {
        return videoFormats;
    }

    public boolean isSkipAudio() {
        return skipAudio;
    }

    public boolean isExtractAudio() {
        return extractAudio;
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    public FileOutput toJSON() {
        Map<String, Object> fileOutput = newHashMap();

        Map<String, Object> video = null;
        if (videoFormats != null && !videoFormats.isEmpty()) {
            video = newHashMap();

            video.put("format", videoFormats);
            fileOutput.put("video", video);
        }

        if (skipAudio) {
            if (video == null) {
                video = newHashMap();
            }

            video.put("skip_audio", true);
            fileOutput.put("video", video);
        }

        if (extractAudio) {
            if (video == null) {
                video = newHashMap();
            }

            video.put("extract_audio", true);
            fileOutput.put("video", video);
        }

        if (audioFormat != null) {
            Map<String, Object> audio = newHashMap();
            audio.put("format", audioFormat);
            fileOutput.put("audio", audio);
        }

        if (imageFormat != null) {
            Map<String, Object> image = newHashMap();
            image.put("format", imageFormat);
            fileOutput.put("image", image);
        }

        return new FileOutput(fileOutput);
    }

    public enum VideoFormat {
        MP4 ("mp4"),
        WEBM ("webm"),
        OGV ("ogv");

        private String videoFormat;

        VideoFormat(String videoFormat) {
            this.videoFormat = videoFormat;
        }

        public String getVideoFormat() {
            return videoFormat;
        }
    }

    public enum AudioFormat {
        MP3 ("mp3"),
        M4A ("m4a"),
        OGG ("ogg");

        private String audioFormat;

        AudioFormat(String audioFormat) {
            this.audioFormat = audioFormat;
        }

        public String getAudioFormat() {
            return audioFormat;
        }
    }

    public enum ImageFormat {
        JPEG ("jpg"),
        PNG ("png");

        private String imageFormat;

        ImageFormat(String imageFormat) {
            this.imageFormat = imageFormat;
        }

        public String getImageFormat() {
            return imageFormat;
        }
    }

    public class FileOutput {

        @SerializedName("file_output")
        Map<String, Object> fileOutput = newHashMap();

        public FileOutput() {
        }

        public FileOutput(Map<String, Object> fileOutput) {
            this.fileOutput = fileOutput;
        }

        public Map<String, Object> getFileOutput() {
            return fileOutput;
        }
    }

}
