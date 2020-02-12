package com.wix.mediaplatform.v7.image;

import com.wix.mediaplatform.v7.image.encoder.JPEG;
import com.wix.mediaplatform.v7.image.filter.*;
import com.wix.mediaplatform.v7.image.framing.*;
import com.wix.mediaplatform.v7.image.parser.FileDescriptorParser;
import com.wix.mediaplatform.v7.image.parser.FileMetadataParser;
import com.wix.mediaplatform.v7.image.parser.ImageUrlParser;
import com.wix.mediaplatform.v7.metadata.FileMetadata;
import com.wix.mediaplatform.v7.service.FileDescriptor;

import java.util.Map;

import static com.google.common.collect.Maps.newTreeMap;

@Deprecated
public class Image {

    private static final String API_VERSION = "v1";

    private String host;

    private String path;

    private String fileName;

    private Metadata metadata;

    private Frame frame;

    private Map<String, Option> options = newTreeMap();

    public Image(String url) {
        ImageUrlParser.parse(this, url);
    }

    public Image(FileDescriptor fileDescriptor) {
        FileDescriptorParser.parse(this, fileDescriptor);
    }

    public Image(FileDescriptor fileDescriptor, String host) {
        FileDescriptorParser.parse(this, fileDescriptor);
        this.setHost(host);
    }

    public Image(FileMetadata fileMetadata) {
        FileMetadataParser.parse(this, fileMetadata);
    }

    public Image(FileMetadata fileMetadata, String host) {
        FileMetadataParser.parse(this, fileMetadata);
        this.setHost(host);
    }

    public Image setHost(String host) {
        this.host = host;
        return this;
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

    @Deprecated
    public Image smartCrop(int width, int height) {
        this.frame = new SmartCrop(width, height);
        return this;
    }

    public Image fit(int width, int height) {
        this.frame = new Fit(width, height);
        return this;
    }

    public Image fill(int width, int height) {
        this.frame = new Fill(width, height);
        return this;
    }

    public Image addOption(Option option) {
        this.options.put(option.getKey(), option);
        return this;
    }

    public Image blur(int percentage) {
        return addOption(new Blur(percentage));
    }

    @Deprecated
    public Image brightness(int brightness) {
        return addOption(new Brightness(brightness));
    }

    @Deprecated
    public Image contrast(int contrast) {
        return addOption(new Contrast(contrast));
    }

    @Deprecated
    public Image hue(int hue) {
        return addOption(new Hue(hue));
    }

    @Deprecated
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

            if (host.endsWith(StringToken.FORWARD_SLASH)) {
                sb.append(host.substring(0, host.length() - 1));
            } else {
                sb.append(host);
            }
        }

        sb.append(path)
                .append(StringToken.FORWARD_SLASH)
                .append(API_VERSION)
                .append(StringToken.FORWARD_SLASH);

        sb.append(frame.serialize());

        int i = options.size();
        for (Map.Entry<String, Option> option : options.entrySet()) {
            if (i > 0) {
                sb.append(StringToken.COMMA);
            }
            sb.append(option.getValue().serialize());
            i--;
        }

        sb.append(StringToken.FORWARD_SLASH).append(fileName);

        if (metadata != null) {
            sb.append(StringToken.HASH).append(metadata.serialize());
        }

        return sb.toString();
    }
}
