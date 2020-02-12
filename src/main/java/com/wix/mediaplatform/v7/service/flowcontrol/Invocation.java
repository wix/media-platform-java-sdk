package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.service.Source;

public class Invocation {

    private String[] entryPoints;

    private Source[] sources;

    //    todo: notification?

    public Invocation() {
    }

    public String[] getEntryPoints() {
        return entryPoints;
    }

    public Invocation setEntryPoints(String[] entryPoints) {
        this.entryPoints = entryPoints;
        return this;
    }

    public Source[] getSources() {
        return sources;
    }

    public Invocation setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }
}
