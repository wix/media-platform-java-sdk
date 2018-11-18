package com.wix.mediaplatform.v6.service.live;

public class LiveStreamRequest {

    private LiveStream.Protocol protocol;

    private Integer maxStreamingSec;

    private Geo geo;

    private Integer connectTimeout;

    private Integer reconnectTimeout;

    private LiveStream.Type streamType;

    private DigitalVideoRecorder digitalVideoRecorder;

    private StateNotification stateNotification;

    public LiveStream.Protocol getProtocol() {
        return protocol;
    }

    public LiveStreamRequest setProtocol(LiveStream.Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public Integer getMaxStreamingSec() {
        return maxStreamingSec;
    }

    public LiveStreamRequest setMaxStreamingSec(Integer maxStreamingSec) {
        this.maxStreamingSec = maxStreamingSec;
        return this;
    }

    public Geo getGeo() {
        return geo;
    }

    public LiveStreamRequest setGeo(Geo geo) {
        this.geo = geo;
        return this;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public LiveStreamRequest setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Integer getReconnectTimeout() {
        return reconnectTimeout;
    }

    public LiveStreamRequest setReconnectTimeout(Integer reconnectTimeout) {
        this.reconnectTimeout = reconnectTimeout;
        return this;
    }

    public LiveStream.Type getStreamType() {
        return streamType;
    }

    public LiveStreamRequest setStreamType(LiveStream.Type streamType) {
        this.streamType = streamType;
        return this;
    }

    public DigitalVideoRecorder getDigitalVideoRecorder() {
        return digitalVideoRecorder;
    }

    public LiveStreamRequest setDigitalVideoRecorder(DigitalVideoRecorder digitalVideoRecorder) {
        this.digitalVideoRecorder = digitalVideoRecorder;
        return this;
    }

    public StateNotification getStateNotification() {
        return stateNotification;
    }

    public LiveStreamRequest setStateNotification(StateNotification stateNotification) {
        this.stateNotification = stateNotification;
        return this;
    }

    public LiveStreamRequest() {
    }

    public LiveStreamRequest(LiveStream.Protocol protocol,
                             Integer maxStreamingSec,
                             Geo geo,
                             Integer connectTimeout,
                             Integer reconnectTimeout,
                             LiveStream.Type streamType,
                             DigitalVideoRecorder digitalVideoRecorder,
                             StateNotification stateNotification) {
        this.protocol = protocol;
        this.maxStreamingSec = maxStreamingSec;
        this.geo = geo;
        this.connectTimeout = connectTimeout;
        this.reconnectTimeout = reconnectTimeout;
        this.streamType = streamType;
        this.digitalVideoRecorder = digitalVideoRecorder;
        this.stateNotification = stateNotification;
    }



}
