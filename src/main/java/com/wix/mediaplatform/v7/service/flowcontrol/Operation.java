package com.wix.mediaplatform.v7.service.flowcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wix.mediaplatform.v7.service.FileDescriptor;
import com.wix.mediaplatform.v7.service.Source;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImportFileOperation.class, name = "file.import"),
        @JsonSubTypes.Type(value = TranscodeOperation.class, name = "av.transcode"),
        @JsonSubTypes.Type(value = CreateUrlSetOperation.class, name = "av.create_urlset"),
})
public abstract class Operation extends Component {

    private Source[] sources;

    private Status status;

    private String[] jobs;

    private FileDescriptor[] results;

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

    public abstract ExtraResults getExtraResults();

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
