package com.wix.mediaplatform.dto.audio;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.FileBaseDTO;

public class AudioDTO extends FileBaseDTO {

    @SerializedName("file_input")
    private Analysis analysis;

    public AudioDTO() {
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public class Analysis {

        private String format;

        private int channels;

        @SerializedName("sample_size")
        private int sampleSize;

        @SerializedName("sample_rate")
        private int sampleRate;

        private long duration;

        private int bitrate;

        public Analysis() {
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

        @Override
        public String toString() {
            return "Analysis{" +
                    "format='" + format + '\'' +
                    ", channels=" + channels +
                    ", sampleSize=" + sampleSize +
                    ", sampleRate=" + sampleRate +
                    ", duration=" + duration +
                    ", bitrate=" + bitrate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AudioDTO{" +
                "analysis=" + analysis +
                "} " + super.toString();
    }
}
