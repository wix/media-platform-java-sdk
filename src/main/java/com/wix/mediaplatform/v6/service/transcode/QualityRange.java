package com.wix.mediaplatform.v6.service.transcode;

public class QualityRange {

    private String maximum;

    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public QualityRange setMaximum(String maximum) {
        this.maximum = maximum;
        return this;
    }

    public String getMinimum() {
        return minimum;
    }

    public QualityRange setMinimum(String minimum) {
        this.minimum = minimum;
        return this;
    }

    @Override
    public String toString() {
        return "QualityRange{" +
                "minimum='" + minimum + '\'' +
                ", maximum='" + minimum + '\'' +
                '}';
    }
}
