package com.wix.mediaplatform.v8.service.image;

import com.wix.mediaplatform.v8.service.Destination;

public class ImageOperationSpecification {

    private String command;

    private Destination destination;

    public ImageOperationSpecification() {
    }

    public String getCommand() {
        return command;
    }

    public ImageOperationSpecification setCommand(String command) {
        this.command = command;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public ImageOperationSpecification setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }
}
