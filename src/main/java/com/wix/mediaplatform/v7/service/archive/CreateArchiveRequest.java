package com.wix.mediaplatform.v7.service.archive;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.Destination;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

import java.util.ArrayList;
import java.util.List;

public class CreateArchiveRequest extends MediaPlatformRequest<CreateArchiveJob> {

    private ArrayList<ArchiveSource> sources = new ArrayList<>();

    private Destination destination;

    private String archiveType;

    CreateArchiveRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/archive/create", CreateArchiveJob.class);
    }

    public CreateArchiveRequest setArchiveSources(ArrayList<ArchiveSource> sources) {
        this.sources = sources;
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

    public String getArchiveType() {
        return archiveType;
    }

    public Destination getDestination() {
        return destination;
    }

    public List<ArchiveSource> getSources() {
        return sources;
    }
}
