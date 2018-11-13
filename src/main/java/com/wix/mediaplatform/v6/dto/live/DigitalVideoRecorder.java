package com.wix.mediaplatform.v6.dto.live;

import com.wix.mediaplatform.v6.service.Destination;

public class DigitalVideoRecorder {

    public DigitalVideoRecorder() {
    }

    private Destination destination;

    public Destination getDestination() {
        return destination;
    }

    public DigitalVideoRecorder setDestination(Destination destination) {
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
