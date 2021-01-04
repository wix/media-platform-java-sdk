package com.wix.mediaplatform.v8.service.file;


import com.wix.mediaplatform.v8.service.Callback;
import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.Specification;

public class ImportFileSpecification implements Specification {

    private String sourceUrl;

    private Destination destination;

    private Callback callback;

    public ImportFileSpecification() {
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Destination getDestination() {
        return destination;
    }

    public Callback getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "ImportFileSpecification{" +
                "sourceUrl='" + sourceUrl + '\'' +
                ", destination=" + destination +
                ", callback=" + callback +
                '}';
    }
}
