package com.wix.mediaplatform.v6.service.flowcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.Source;

public class Operation extends Component {

    private Source[] sources;

    private Status status;

    private String[] jobs;

    private FileDescriptor[] results;

    private ExtraResults extraResults;

    public Operation() {
    }

    public Source[] getSources() {
        return sources;
    }

    public Operation setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Operation setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String[] getJobs() {
        return jobs;
    }

    public Operation setJobs(String[] jobs) {
        this.jobs = jobs;
        return this;
    }

    public FileDescriptor[] getResults() {
        return results;
    }

    public Operation setResults(FileDescriptor[] results) {
        this.results = results;
        return this;
    }

    public ExtraResults getExtraResults() {
        return extraResults;
    }

    public Operation setExtraResults(ExtraResults extraResults) {
        this.extraResults = extraResults;
        return this;
    }

    public enum Status {

        @JsonProperty("waiting")
        waiting("waiting"),
        @JsonProperty("aborted")
        aborted("aborted"),
        @JsonProperty("working")
        working("working"),
        @JsonProperty("success")
        success("success"),
        @JsonProperty("error")
        error("error");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromString(String typeString) {
            return Status.valueOf(typeString);
        }
    }
}
