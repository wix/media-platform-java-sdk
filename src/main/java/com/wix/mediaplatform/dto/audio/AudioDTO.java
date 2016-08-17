package com.wix.mediaplatform.dto.audio;

import com.wix.mediaplatform.dto.FileBaseDTO;

public class AudioDTO extends FileBaseDTO {

    private AudioFile inputFile;

    public AudioDTO() {
    }

    public AudioFile getInputFile() {
        return inputFile;
    }

    public class AudioFile {

        private String format;

        private int channels;

        private int sampleSize;

        private int sampleRate;

        private long duration;

        private int bitrate;

        public AudioFile() {
        }

        public String getFormat() {
            return format;
        }

        public int getChannels() {
            return channels;
        }

        public int getSampleSize() {
            return sampleSize;
        }

        public int getSampleRate() {
            return sampleRate;
        }

        public long getDuration() {
            return duration;
        }

        public int getBitrate() {
            return bitrate;
        }
    }
}