package com.wix.mediaplatform.image;

import com.google.common.escape.Escaper;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;
import static com.wix.mediaplatform.image.StringToken.KEY_HEIGHT;
import static com.wix.mediaplatform.image.StringToken.KEY_WIDTH;

public class Metadata {

    private static final Escaper escaper = urlFragmentEscaper();

    public static final String KEY_MIME_TYPE = "mt";

    private int width;

    private int height;

    private String mimeType;

    public Metadata(int width, int height, String mimeType) {
        this.width = width;
        this.height = height;
        this.mimeType = mimeType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMimeType() {
        return mimeType;
    }

    /**
     * @return the URL fragment (after the #) string representation
     */
    public String serialize() {
            return KEY_WIDTH + "_" + width + "," + KEY_HEIGHT + "_" + height + "," + KEY_MIME_TYPE + "_" + escaper.escape(mimeType);
    }
}
