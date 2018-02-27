package com.wix.mediaplatform.dto.request;

import com.wix.mediaplatform.dto.job.ArchiveSource;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.Source;

import java.util.ArrayList;

public class CreateArchiveRequest {

    private ArrayList<Source> sources = new ArrayList<>();

    private Destination destination;

    private String archiveType;

    public CreateArchiveRequest() {
    }

    public CreateArchiveRequest setSources(ArrayList<Source> sources) {
        this.sources = sources;
        return this;
    }


    public CreateArchiveRequest addSource(Source source) {
        this.sources.add(source);
        return this;
    }

    public CreateArchiveRequest addSource(ArchiveSource source) {
        this.sources.add(source);
        return this;
    }

    public CreateArchiveRequest setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public CreateArchiveRequest setArchiveType(String archiveType) {
        this.archiveType = archiveType;
        return this;
    }
}
