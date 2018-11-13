package com.wix.mediaplatform.v6.dto.live;

import java.util.ArrayList;

public class LiveStream {
    private Integer connectTimeout;

    private String dateCreated;

    private String dateUpdated;

    private Integer duration;

    private DigitalVideoRecorder digitalVideoRecorder;

    private EnforcedStreamParams enforcedStreamParams;

    private String errorMessage;

    private String id;

    private Integer maxPublishDuration;

    private ArrayList<PlaybackUrl> playbackUrls;

    private String probeResult;

    private String projectId;

    private Protocol protocol;

    private PublishEndpoint publishEndpoint;

    private Integer reconnectTimeout;

    private State state;

    private StateNotification stateNotification;

    private Type streamType;

    private String streamerId;

    private Boolean success;


    public LiveStream() {
        playbackUrls = new ArrayList<PlaybackUrl>();
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public LiveStream setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public LiveStream setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public LiveStream setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public LiveStream setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public DigitalVideoRecorder getDigitalVideoRecorder() {
        return digitalVideoRecorder;
    }

    public LiveStream setDigitalVideoRecorder(DigitalVideoRecorder digitalVideoRecorder) {
        this.digitalVideoRecorder = digitalVideoRecorder;
        return this;
    }

    public EnforcedStreamParams getEnforcedStreamParams() {
        return enforcedStreamParams;
    }

    public LiveStream setEnforcedStreamParams(EnforcedStreamParams enforcedStreamParams) {
        this.enforcedStreamParams = enforcedStreamParams;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LiveStream setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getId() {
        return id;
    }

    public LiveStream setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getMaxPublishDuration() {
        return maxPublishDuration;
    }

    public LiveStream setMaxPublishDuration(Integer maxPublishDuration) {
        this.maxPublishDuration = maxPublishDuration;
        return this;
    }

    public ArrayList<PlaybackUrl> getPlaybackUrls() {
        return playbackUrls;
    }

    public LiveStream addPlaybackUrl(PlaybackUrl playbackUrl) {
        this.playbackUrls.add(playbackUrl);
        return this;
    }

    public LiveStream setPlaybackUrls(ArrayList<PlaybackUrl> playbackUrls) {
        this.playbackUrls = playbackUrls;
        return this;
    }

    public String getProbeResult() {
        return probeResult;
    }

    public LiveStream setProbeResult(String probeResult) {
        this.probeResult = probeResult;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public LiveStream setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public LiveStream setProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public PublishEndpoint getPublishEndpoint() {
        return publishEndpoint;
    }

    public LiveStream setPublishEndpoint(PublishEndpoint publishEndpoint) {
        this.publishEndpoint = publishEndpoint;
        return this;
    }

    public Integer getReconnectTimeout() {
        return reconnectTimeout;
    }

    public LiveStream setReconnectTimeout(Integer reconnectTimeout) {
        this.reconnectTimeout = reconnectTimeout;
        return this;
    }

    public State getState() {
        return state;
    }

    public LiveStream setState(State state) {
        this.state = state;
        return this;
    }

    public StateNotification getStateNotification() {
        return stateNotification;
    }

    public LiveStream setStateNotification(StateNotification stateNotification) {
        this.stateNotification = stateNotification;
        return this;
    }

    public Type getStreamType() {
        return streamType;
    }

    public LiveStream setStreamType(Type streamType) {
        this.streamType = streamType;
        return this;
    }

    public String getStreamerId() {
        return streamerId;
    }

    public LiveStream setStreamerId(String streamerId) {
        this.streamerId = streamerId;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public LiveStream setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public enum State {
        created("created"),
        streaming("working"),
        pending_reconnect("pending_reconnect"),
        dvr_processing("dvr_processing"),
        closed("closed");

        private final String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static State fromString(String typeString) {
            return State.valueOf(typeString);
        }
    }

    public enum Protocol {
        rtmp("rtmp");

        private final String value;

        Protocol(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Protocol fromString(String typeString) {
            return Protocol.valueOf(typeString);
        }
    }

    public enum Type {
        live("live"),
        event("event");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type fromString(String typeString) {
            return Type.valueOf(typeString);
        }
    }

}
