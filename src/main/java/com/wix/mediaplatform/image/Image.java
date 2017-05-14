package com.wix.mediaplatform.image;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.metadata.FileMetadata;
import com.wix.mediaplatform.image.encoder.JPEG;
import com.wix.mediaplatform.image.filter.*;
import com.wix.mediaplatform.image.framing.Crop;
import com.wix.mediaplatform.image.framing.Frame;
import com.wix.mediaplatform.image.framing.SmartCrop;
import com.wix.mediaplatform.image.parser.FileDescriptorParser;
import com.wix.mediaplatform.image.parser.FileMetadataParser;
import com.wix.mediaplatform.image.parser.ImageUrlParser;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.image.StringToken.*;

public class Image {

    private static final String API_VERSION = "v1";

    private String host;

    private String path;

    private String fileName;

    private Metadata metadata;

    private Frame frame;

    private Map<String, Option> options = newHashMap();

    public Image(String url) {
        ImageUrlParser.parse(this, url);
    }

    public Image(FileDescriptor fileDescriptor) {
        FileDescriptorParser.parse(this, fileDescriptor);
    }

    public Image(FileMetadata fileMetadata) {
        FileMetadataParser.parse(this, fileMetadata);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Image setPath(String path) {
        this.path = path;
        return this;
    }

    public Image setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Image setMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public Image crop(int width, int height, int x, int y, float scaleFactor) {
        this.frame = new Crop(x, y, width, height, scaleFactor);
        return this;
    }

    public Image smartCrop(int width, int height) {
        this.frame = new SmartCrop(width, height);
        return this;
    }

    public Image addOption(Option option) {
        this.options.put(option.getKey(), option);
        return this;
    }

    public Image blur(int percentage) {
        return addOption(new Blur(percentage));
    }

    public Image brightness(int brightness) {
        return addOption(new Brightness(brightness));
    }

    public Image contrast(int contrast) {
        return addOption(new Contrast(contrast));
    }

    public Image hue(int hue) {
        return addOption(new Hue(hue));
    }

    public Image saturation(int saturation) {
        return addOption(new Saturation(saturation));
    }

    public Image unsharpMask(float radius, float amount, float threshold) {
        return addOption(new UnsharpMask(radius, amount, threshold));
    }

    public Image jpeg(int quality) {
        return addOption(new JPEG(quality));
    }

    public String toUrl() {

        StringBuilder sb = new StringBuilder();

        if (host != null && !host.isEmpty()) {
            if (!host.startsWith("http") && !host.startsWith("//")) {
                sb.append("//");
            }

            if (host.endsWith(FORWARD_SLASH)) {
                sb.append(host.substring(0, host.length() - 1));
            } else {
                sb.append(host);
            }
        }

        sb.append(path)
                .append(FORWARD_SLASH)
                .append(API_VERSION)
                .append(FORWARD_SLASH);

        sb.append(frame.serialize());

        int i = options.size();
        for (Map.Entry<String, Option> option : options.entrySet()) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(option.getValue().serialize());
            i--;
        }

        sb.append(FORWARD_SLASH).append(fileName);

        if (metadata != null) {
            sb.append(HASH).append(metadata.serialize());
        }

        return sb.toString();
    }
}
