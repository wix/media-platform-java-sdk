package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import com.wix.mediaplatform.image.option.Option;
import com.wix.mediaplatform.image.option.blur.Blur;
import com.wix.mediaplatform.image.option.brightness.Brightness;
import com.wix.mediaplatform.image.option.contrast.Contrast;
import com.wix.mediaplatform.image.option.encoding.jpeg.Baseline;
import com.wix.mediaplatform.image.option.encoding.jpeg.Quality;
import com.wix.mediaplatform.image.option.hue.Hue;
import com.wix.mediaplatform.image.option.negative.Negative;
import com.wix.mediaplatform.image.option.oil.Oil;
import com.wix.mediaplatform.image.option.pixelate.Pixelate;
import com.wix.mediaplatform.image.option.pixelate.PixelateFaces;
import com.wix.mediaplatform.image.option.redeye.RedeyeRemover;
import com.wix.mediaplatform.image.option.saturation.Saturation;
import com.wix.mediaplatform.image.option.sharpen.Sharpen;
import com.wix.mediaplatform.image.option.unsharp.UnsharpMask;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.SortedSet;

import static com.google.common.collect.Sets.newTreeSet;

public abstract class Operation<T extends Operation<T>> {

    private static final String API_VERSION = "v1";

    private static final String FORWARD_SLASH = "/";

    private static final String KEY_WIDTH = "w";

    private static final String KEY_HEIGHT = "h";

    static final String SEPARATOR = "_";

    static final String COMMA = ",";

    private static final Comparator<Option> OPTION_COMPARATOR = new Comparator<Option>() {
        @Override
        public int compare(Option o1, Option o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    private String name;

    private String baseUrl;

    private String fileId;

    private String fileName;

    private int width;

    private int height;

    private OriginalData originalData;

    SortedSet<Option> options = newTreeSet(OPTION_COMPARATOR);

    Operation(String name, String baseUrl, String fileId, String fileName, int width, int height, @Nullable OriginalData originalData) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.fileId = fileId;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.originalData = originalData;
    }

    public T width(int width) {
        this.width = width;
        return (T) this;
    }

    public T height(int height) {
        this.height = height;
        return (T) this;
    }

    public T brightness(int brightness) {
        options.add(new Brightness(brightness));
        return (T) this;
    }

    public T contrast(int contrast) {
        options.add(new Contrast(contrast));
        return (T) this;
    }

    public T hue(int hue) {
        options.add(new Hue(hue));
        return (T) this;
    }

    public T saturation(int saturation) {
        options.add(new Saturation(saturation));
        return (T) this;
    }

    public T blur(int percentage) {
        options.add(new Blur(percentage));
        return (T) this;
    }

    public T negative() {
        options.add(new Negative());
        return (T) this;
    }

    public T oil() {
        options.add(new Oil());
        return (T) this;
    }

    public T pixelate(int pixels) {
        options.add(new Pixelate(pixels));
        return (T) this;
    }

    public T pixelateFaces(int pixels) {
        options.add(new PixelateFaces(pixels));
        return (T) this;
    }

    public T removeRedeye() {
        options.add(new RedeyeRemover());
        return (T) this;
    }

    public T sharpen(float radius) {
        options.add(new Sharpen(radius));
        return (T) this;
    }

    public T unsharpMask(float radius, float amount, float threshold) {
        options.add(new UnsharpMask(radius, amount, threshold));
        return (T) this;
    }

    public T baseline() {
        options.add(new Baseline());
        return (T) this;
    }

    public T quality(int quality) {
        options.add(new Quality(quality));
        return (T) this;
    }

    public String toUrl() {

        StringBuilder sb = new StringBuilder();

        if (baseUrl != null && !baseUrl.isEmpty()) {
            if (!baseUrl.startsWith("http") && !baseUrl.startsWith("//")) {
                    sb.append("//");
            }

            if (baseUrl.endsWith(FORWARD_SLASH)) {
                sb.append(baseUrl.substring(0, baseUrl.length() - 1));
            } else {
                sb.append(baseUrl);
            }
        }

        sb.append(FORWARD_SLASH)
                .append(fileId)
                .append(FORWARD_SLASH)
                .append(API_VERSION)
                .append(FORWARD_SLASH)
                .append(name)
                .append(FORWARD_SLASH);

        sb.append(serialize());

        int i = options.size();
        for (Option option : options) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(option.serialize());
            i--;
        }

        sb.append(FORWARD_SLASH).append(fileName);

        if (originalData != null) {
            sb.append("#").append(originalData.serialize());
        }

        return sb.toString();
    }

    /**
     * @return the partial URL of the mandatory operations params
     * override to add additional params
     */
    protected String serialize() {
        return  KEY_WIDTH + SEPARATOR + width + COMMA + KEY_HEIGHT + SEPARATOR + height;
    }
}
