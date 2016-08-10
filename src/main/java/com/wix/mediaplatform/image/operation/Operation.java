package com.wix.mediaplatform.image.operation;

import com.wix.mediaplatform.image.OriginalData;
import com.wix.mediaplatform.image.option.Option;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.SortedSet;

import static com.google.common.collect.Sets.newTreeSet;

public abstract class Operation {

    private static final String API_VERSION = "v1";

    private static final String FORWARD_SLASH = "/";

    private static final Comparator<Option> OPTION_COMPARATOR = new Comparator<Option>() {
        @Override
        public int compare(Option o1, Option o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    private String name;

    private String baseUrl;

    private String fileId;

    private String fileName;

    private OriginalData originalData;

    private SortedSet<Option> options = newTreeSet(OPTION_COMPARATOR);

    public Operation(String name, String baseUrl, String fileId, String fileName, @Nullable OriginalData originalData) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.fileId = fileId;
        this.fileName = fileName;
        this.originalData = originalData;
    }

    public String toUrl() {

        StringBuilder sb = new StringBuilder();

        if (baseUrl != null && !baseUrl.isEmpty()) {
            if (!baseUrl.startsWith("http") && !baseUrl.startsWith("//")) {
                    sb.append("//");
            }

            if (baseUrl.endsWith(FORWARD_SLASH)) {
                sb.append(baseUrl.substring(0, baseUrl.length() - 1));
            } else {
                sb.append(baseUrl);
            }
        }

        sb.append(FORWARD_SLASH)
                .append(fileId)
                .append(FORWARD_SLASH)
                .append(API_VERSION)
                .append(FORWARD_SLASH)
                .append(name)
                .append(FORWARD_SLASH);

        int i = options.size();
        for (Option option : options) {
            sb.append(option.serialize());
            i--;
            if (i > 1) {
                sb.append(",");
            }
        }

        sb.append(FORWARD_SLASH).append(fileName);

        if (originalData != null) {
            sb.append("#").append(originalData.serialize());
        }

        return sb.toString();
    }
}
