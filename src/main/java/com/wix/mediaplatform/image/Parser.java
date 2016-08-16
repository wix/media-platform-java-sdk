package com.wix.mediaplatform.image;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.dto.image.ImageDTO;
import com.wix.mediaplatform.image.operation.Operation;
import com.wix.mediaplatform.image.option.Option;
import com.wix.mediaplatform.image.option.alignment.Align;
import com.wix.mediaplatform.image.option.background.Background;
import com.wix.mediaplatform.image.option.blur.Blur;
import com.wix.mediaplatform.image.option.brightness.Brightness;
import com.wix.mediaplatform.image.option.contrast.Contrast;
import com.wix.mediaplatform.image.option.encoding.jpeg.Baseline;
import com.wix.mediaplatform.image.option.encoding.jpeg.Quality;
import com.wix.mediaplatform.image.option.hue.Hue;
import com.wix.mediaplatform.image.option.negative.Negative;
import com.wix.mediaplatform.image.option.oil.Oil;
import com.wix.mediaplatform.image.option.pixelate.Pixelate;
import com.wix.mediaplatform.image.option.pixelate.PixelateFaces;
import com.wix.mediaplatform.image.option.redeye.RedeyeRemover;
import com.wix.mediaplatform.image.option.saturation.Saturation;
import com.wix.mediaplatform.image.option.sharpen.Sharpen;
import com.wix.mediaplatform.image.option.unsharp.UnsharpMask;
import com.wix.mediaplatform.image.option.upscale.Upscale;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.image.OriginalData.*;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Parser {

    private static final Map<String, Class> OPTIONS = ImmutableMap.<String, Class>builder()
            .put(Align.KEY, Align.class)
            .put(Background.KEY, Background.class)
            .put(Blur.KEY, Blur.class)
            .put(Brightness.KEY, Brightness.class)
            .put(Contrast.KEY, Contrast.class)
            .put(Baseline.KEY, Baseline.class)
            .put(Quality.KEY, Quality.class)
            .put(Hue.KEY, Hue.class)
            .put(Negative.KEY, Negative.class)
            .put(Oil.KEY, Oil.class)
            .put(Pixelate.KEY, Pixelate.class)
            .put(PixelateFaces.KEY, PixelateFaces.class)
            .put(RedeyeRemover.KEY, RedeyeRemover.class)
            .put(Saturation.KEY, Saturation.class)
            .put(Sharpen.KEY, Sharpen.class)
            .put(UnsharpMask.KEY, UnsharpMask.class)
            .put(Upscale.KEY, Upscale.class)
            .build();

    public static ImageRequest fromDto(String host, ImageDTO imageDTO) {
        return new ImageRequest(host + "/" + imageDTO.getBaseUrl(), imageDTO.getFileName(), imageDTO.getOriginalFileName(),
                new OriginalData(imageDTO.getWidth(), imageDTO.getHeight(), imageDTO.getMimeType()));
    }

    public static ImageRequest fromUrl(String url) throws URISyntaxException {
        ExplodedUrl explodedUrl = parseUrl(url);

        return fromExplodedUrl(explodedUrl);
    }

    public static Operation operationFromUrl(String url) throws URISyntaxException {
        ExplodedUrl explodedUrl = parseUrl(url);
        ImageRequest imageRequest = fromExplodedUrl(explodedUrl);

        Map<String, String[]> options = newHashMap();
        String[] parts = explodedUrl.getOptions().split(",");
        for (String part : parts) {
            String[] option = part.split("_");
            options.put(option[0], Arrays.copyOfRange(option, 1, option.length));
        }

        Method method = findMethod(imageRequest, explodedUrl.getOperation());
        if (method == null) {
            throw new URISyntaxException(url, "operation not supported");
        }

        Operation operation;
        try {
            switch (method.getName()) {
                case "crop":
                    operation = (Operation) method.invoke(imageRequest, parseInt(options.get("w")[0]), parseInt(options.get("h")[0]),
                            parseInt(options.get("x")[0]),
                            parseInt(options.get("y")[0]),
                            parseFloat(options.get("scl")[0]));

                    options.remove("w");
                    options.remove("h");
                    options.remove("x");
                    options.remove("y");
                    options.remove("scl");
                    break;
                default:
                    operation = (Operation) method.invoke(imageRequest, parseInt(options.get("w")[0]), parseInt(options.get("h")[0]));
                    options.remove("w");
                    options.remove("h");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new URISyntaxException(url, "operation not supported");
        }

        for (Map.Entry<String, String[]> opt : options.entrySet()) {
            try {
                Option option = (Option) OPTIONS.get(opt.getKey()).newInstance();
                option.deserialize(opt.getValue());
                operation.addOption(option);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                throw new URISyntaxException(url, "option not supported");
            }
        }

        return operation;
    }

    private static ImageRequest fromExplodedUrl(ExplodedUrl explodedUrl) {
        return new ImageRequest(explodedUrl.getBaseUrl(), explodedUrl.getImageId(), explodedUrl.getFileName(), explodedUrl.getOriginalData());
    }

    @Nullable
    private static Method findMethod(ImageRequest imageRequest, String methodName) {
        Method[] methods = imageRequest.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        return null;
    }

    private static ExplodedUrl parseUrl(String url) throws URISyntaxException {
        URI u = new URI(url);

        OriginalData originalData = parseOriginalData(u.getFragment());

        String path = u.getPath();
        String[] parts = path.split("/");
        if (parts.length < 8) {
            throw new URISyntaxException(url, "not a valid media platform image URL");
        }
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
            if (params.length < 2) {
                continue;
            }
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
