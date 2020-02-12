package com.wix.mediaplatform.v7.service.archive;


import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.Destination;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

import java.util.ArrayList;


public class CreateArchiveManifestRequest extends MediaPlatformRequest<FileDescriptor> {

    private String name;

    private ArrayList<ArchiveSource> sources = new ArrayList<>();

    private Destination destination;

    private String algorithm;

    protected CreateArchiveManifestRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/archive/create/manifest", FileDescriptor.class);
    }

    public String getName() {
        return name;
    }

    public CreateArchiveManifestRequest setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<ArchiveSource> getSources() {
        return sources;
    }

    public CreateArchiveManifestRequest setSources(ArrayList<ArchiveSource> sources) {
        this.sources = sources;
        return this;
    }

    public CreateArchiveManifestRequest addSource(ArchiveSource source) {
        this.sources.add(source);
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public CreateArchiveManifestRequest setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public CreateArchiveManifestRequest setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }
}
