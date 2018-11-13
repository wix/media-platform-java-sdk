package com.wix.mediaplatform.v6.image;

import com.google.common.escape.Escaper;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;

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
            return StringToken.KEY_WIDTH + "_" + width + "," + StringToken.KEY_HEIGHT + "_" + height + "," + KEY_MIME_TYPE + "_" + escaper.escape(mimeType);
    }
}
