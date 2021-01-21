package com.wix.mediaplatform.v8.service.archive;


import com.wix.mediaplatform.v8.service.Destination;
import com.wix.mediaplatform.v8.service.Source;
import com.wix.mediaplatform.v8.service.Specification;
import java.util.*; 

public class CreateArchiveSpecification implements Specification {

    private Source[] sources;

    private Destination destination;

    private String archiveType;

    public CreateArchiveSpecification() {}

    public Source[] getSources() {
        return sources;
    }

    public Destination getDestination() {
        return destination;
    }

    public String getArchiveType() { return archiveType; }

    @Override
    public String toString() {
        return "CreateArchiveSpecification{" +
                "source=" + Arrays.toString(sources) +
                ", destination=" + destination +
                ", archiveType=" + archiveType +
                '}';
    }
}
