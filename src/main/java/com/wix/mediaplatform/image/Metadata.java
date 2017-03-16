package com.wix.mediaplatform.image;

import com.google.common.escape.Escaper;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;

public class Metadata {

    private static final Escaper escaper = urlFragmentEscaper();

    static final String WIDTH_KEY = "w";

    static final String HEIGHT_KEY = "h";

    static final String MIME_TYPE_KEY = "mt";

    private int width;

    private int height;

    private String mimeType;

    Metadata(int width, int height, String mimeType) {
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
            return WIDTH_KEY + "_" + width + "," + HEIGHT_KEY + "_" + height + "," + MIME_TYPE_KEY + "_" + escaper.escape(mimeType);
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "width=" + width +
                ", height=" + height +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }
}
