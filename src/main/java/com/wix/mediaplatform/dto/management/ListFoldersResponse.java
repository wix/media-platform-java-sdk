package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.folder.FolderDTO;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ListFoldersResponse {

    @SerializedName("ts")
    private long timeStamp;

    private Set<FolderDTO> folders = newHashSet();

    public ListFoldersResponse(long timeStamp, Set<FolderDTO> folders) {
        this.timeStamp = timeStamp;
        this.folders = folders;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Set<FolderDTO> getFolders() {
        return folders;
    }
}
