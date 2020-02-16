package com.wix.mediaplatform.v7.service.live;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

public class OpenLiveStreamRequest extends MediaPlatformRequest<LiveStream> {

    private LiveStream.Protocol protocol;

    private Integer maxStreamingSec;

    private Geo geo;

    private Integer connectTimeout;

    private Integer reconnectTimeout;

    private LiveStream.Type streamType;

    private DigitalVideoRecorder digitalVideoRecorder;

    private StateNotification stateNotification;

    public OpenLiveStreamRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "POST", baseUrl + "/live/stream", LiveStream.class);
    }

    public LiveStream.Protocol getProtocol() {
        return protocol;
    }

    public OpenLiveStreamRequest setProtocol(LiveStream.Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public Integer getMaxStreamingSec() {
        return maxStreamingSec;
    }

    public OpenLiveStreamRequest setMaxStreamingSec(Integer maxStreamingSec) {
        this.maxStreamingSec = maxStreamingSec;
        return this;
    }

    public Geo getGeo() {
        return geo;
    }

    public OpenLiveStreamRequest setGeo(Geo geo) {
        this.geo = geo;
        return this;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public OpenLiveStreamRequest setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Integer getReconnectTimeout() {
        return reconnectTimeout;
    }

    public OpenLiveStreamRequest setReconnectTimeout(Integer reconnectTimeout) {
        this.reconnectTimeout = reconnectTimeout;
        return this;
    }

    public LiveStream.Type getStreamType() {
        return streamType;
    }

    public OpenLiveStreamRequest setStreamType(LiveStream.Type streamType) {
        this.streamType = streamType;
        return this;
    }

    public DigitalVideoRecorder getDigitalVideoRecorder() {
        return digitalVideoRecorder;
    }

    public OpenLiveStreamRequest setDigitalVideoRecorder(DigitalVideoRecorder digitalVideoRecorder) {
        this.digitalVideoRecorder = digitalVideoRecorder;
        return this;
    }

    public StateNotification getStateNotification() {
        return stateNotification;
    }

    public OpenLiveStreamRequest setStateNotification(StateNotification stateNotification) {
        this.stateNotification = stateNotification;
        return this;
    }
}
