package com.wix.mediaplatform.dto.VideoDTO;

import com.wix.mediaplatform.dto.BaseDTO;

import java.util.Set;

public class VideoDTO extends BaseDTO {

    private int height;

    private int width;

    private String iconUrl;

    private VideoFile inputFile;

    private OutputFiles outputFiles;

    public VideoDTO() {
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public VideoFile getInputFile() {
        return inputFile;
    }

    public OutputFiles getOutputFiles() {
        return outputFiles;
    }

    private class VideoFile extends BaseFile {

        private String tag;

        private int fps;

        private int videoBitrate;

        private int audioBitrate;

        private long duration;

        private String quality;

        private String displayAspectRatio;

        private String sampleAspectRatio;

        private float rotation;

        private String type;

        public VideoFile() {
        }

        public String getTag() {
            return tag;
        }

        public int getFps() {
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
    }

    private class ImageFile extends BaseFile {

    }

    private class OutputFiles {

        private Set<ImageFile> image;

        private Set<VideoFile> video;

        public OutputFiles() {
        }

        public Set<ImageFile> getImage() {
            return image;
        }

        public Set<VideoFile> getVideo() {
            return video;
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
    }
}
