package com.wix.mediaplatform.dto.live;

import com.wix.mediaplatform.dto.job.Destination;

public class Dvr {

    public Dvr() {
    }

    private Destination destination;

    public Destination getDestination() {
        return destination;
    }

    public Dvr setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destination=" + destination +
                '}';
    }
}
