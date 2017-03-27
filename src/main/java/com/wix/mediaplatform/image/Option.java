package com.wix.mediaplatform.image;

public abstract class Option {

//    protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private String key;

    public Option(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String decimalString(float value) {
        return String.format( "%.2f", value);
    }

    public abstract String serialize();

    public abstract Option deserialize(String... params);
}
