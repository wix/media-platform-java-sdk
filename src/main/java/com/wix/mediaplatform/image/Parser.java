package com.wix.mediaplatform.image;

import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.image.operation.Operation;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.image.OriginalData.*;
import static java.lang.Integer.parseInt;

public class Parser {

    public static ImageRequest fromDto(String host, ImageDTO imageDTO) {
        return new ImageRequest(host + "/" + imageDTO.getBaseUrl(), imageDTO.getFileName(), imageDTO.getOriginalFileName(),
                new OriginalData(imageDTO.getWidth(), imageDTO.getHeight(), imageDTO.getMimeType()));
    }

    public static ImageRequest fromUrl(String url) throws URISyntaxException {
        ExplodedUrl explodedUrl = parseUrl(url);

        return new ImageRequest(explodedUrl.getBaseUrl(), explodedUrl.getImageId(), explodedUrl.getFileName(), explodedUrl.getOriginalData());

    }

    public static Operation operationFromUrl(String url) {
        //TODO
        return null;
    }

    private static ExplodedUrl parseUrl(String url) throws URISyntaxException {
        URI u = new URI(url);

        OriginalData originalData = parseOriginalData(u.getFragment());

        String path = u.getPath();
        String[] parts = path.split("/");
        return new ExplodedUrl(u.getScheme(), u.getHost(), (u.getPort() == -1) ? null : u.getPort(), parts[1], parts[2], parts[3], parts[4],
                parts[5], parts[6], parts[7], originalData);
    }

    @Nullable
    private static OriginalData parseOriginalData(String fragment) {
        if (fragment == null) {
            return null;
        }

        Map<String, String> values = newHashMap();
        String[] parts = fragment.split(",");
        for (String part : parts) {
            String[] params = part.split("_");
            values.put(params[0], params[1]);
        }

        if (values.get(WIDTH_KEY) == null || values.get(HEIGHT_KEY) == null || values.get(MIME_TYPE_KEY) == null) {
            return null;
        }

        return new OriginalData(parseInt(values.get(WIDTH_KEY)), parseInt(values.get(HEIGHT_KEY)), values.get(MIME_TYPE_KEY));
    }

    private static class ExplodedUrl {

        private String scheme;
        
        private String host;
        
        private Integer port;
        
        private String bucket;

        private String folder;

        private String imageId;

        private String version;

        private String operation;

        private String options;

        private String fileName;

        private OriginalData originalData;

        ExplodedUrl(String scheme, String host, @Nullable Integer port, String bucket, String folder, String imageId, String version, String operation, String options, String fileName, OriginalData originalData) {
            this.scheme = scheme;
            this.host = host;
            this.port = port;
            this.bucket = bucket;
            this.folder = folder;
            this.imageId = imageId;
            this.version = version;
            this.operation = operation;
            this.options = options;
            this.fileName = fileName;
            this.originalData = originalData;
        }

        String getScheme() {
            return scheme;
        }

        String getHost() {
            return host;
        }

        Integer getPort() {
            return port;
        }

        String getBucket() {
            return bucket;
        }

        String getFolder() {
            return folder;
        }

        String getImageId() {
            return imageId;
        }

        String getVersion() {
            return version;
        }

        String getOperation() {
            return operation;
        }

        String getOptions() {
            return options;
        }

        String getFileName() {
            return fileName;
        }

        OriginalData getOriginalData() {
            return originalData;
        }

        String getBaseUrl() {
            return (scheme != null ? scheme + "://" : "//") +
                    host + (port != null ? ":" + port : "") + "/" +
                    bucket + "/" +
                    folder;
        }
    }
}
