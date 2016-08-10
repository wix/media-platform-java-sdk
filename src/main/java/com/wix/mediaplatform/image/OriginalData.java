package com.wix.mediaplatform.image;

import com.google.common.escape.Escaper;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;

public class OriginalData {

    private final Escaper escaper = urlFragmentEscaper();

    private int width;

    private int height;

    private String mimeType;

    public OriginalData(int width, int height, String mimeType) {
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
            return "w_" + width + ",h_" + height + ",mt_" + escaper.escape(mimeType);
    }
}
