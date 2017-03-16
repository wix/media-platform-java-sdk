package com.wix.mediaplatform.dto.video;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.FileDescriptor;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class VideoDescriptor extends FileDescriptor {

    private int height;

    private int width;

    @SerializedName("file_input")
    private VideoFile inputFile;

    @SerializedName("file_output")
    private OutputFiles outputFiles;

    public VideoDescriptor() {
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public VideoFile getInputFile() {
        return inputFile;
    }

    public OutputFiles getOutputFiles() {
        return outputFiles;
    }

    private class VideoFile extends BaseFile {

        private String tag;

        private String fps;

        @SerializedName("video_bitrate")
        private int videoBitrate;

        @SerializedName("audio_bitrate")
        private int audioBitrate;

        private long duration;

        private String quality;

        @SerializedName("display_aspect_ratio")
        private String displayAspectRatio;

        @SerializedName("sample_aspect_ratio")
        private String sampleAspectRatio;

        private float rotation;

        private String type;

        public VideoFile() {
        }

        public String getTag() {
            return tag;
        }

        public String getFps() {
            return fps;
        }

        public int getVideoBitrate() {
            return videoBitrate;
        }

        public int getAudioBitrate() {
            return audioBitrate;
        }

        public long getDuration() {
            return duration;
        }

        public String getQuality() {
            return quality;
        }

        public String getDisplayAspectRatio() {
            return displayAspectRatio;
        }

        public String getSampleAspectRatio() {
            return sampleAspectRatio;
        }

        public float getRotation() {
            return rotation;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "VideoFile{" +
                    "tag='" + tag + '\'' +
                    ", fps='" + fps + '\'' +
                    ", videoBitrate=" + videoBitrate +
                    ", audioBitrate=" + audioBitrate +
                    ", duration=" + duration +
                    ", quality='" + quality + '\'' +
                    ", displayAspectRatio='" + displayAspectRatio + '\'' +
                    ", sampleAspectRatio='" + sampleAspectRatio + '\'' +
                    ", rotation=" + rotation +
                    ", type='" + type + '\'' +
                    "} " + super.toString();
        }
    }

    private class ImageFile extends BaseFile {

    }

    private class OutputFiles {

        @SerializedName("image")
        private Set<ImageFile> images = newHashSet();

        @SerializedName("video")
        private Set<VideoFile> videos = newHashSet();;

        public OutputFiles() {
        }

        public Set<ImageFile> getImages() {
            return images;
        }

        public Set<VideoFile> getVideos() {
            return videos;
        }

        @Override
        public String toString() {
            return "OutputFiles{" +
                    "images=" + images +
                    ", videos=" + videos +
                    '}';
        }
    }

    private abstract class BaseFile {

        private String status;

        private boolean secure;

        private String url;

        private String format;

        private int width;

        private int height;

        BaseFile() {
        }

        public String getStatus() {
            return status;
        }

        public boolean isSecure() {
            return secure;
        }

        public String getUrl() {
            return url;
        }

        public String getFormat() {
            return format;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return "BaseFile{" +
                    "status='" + status + '\'' +
                    ", secure=" + secure +
                    ", url='" + url + '\'' +
                    ", format='" + format + '\'' +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VideoDescriptor{" +
                "height=" + height +
                ", width=" + width +
                ", inputFile=" + inputFile +
                ", outputFiles=" + outputFiles +
                "} " + super.toString();
    }
}
