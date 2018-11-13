package com.wix.mediaplatform.v6.image.parser;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.v6.image.Image;
import com.wix.mediaplatform.v6.image.Metadata;
import com.wix.mediaplatform.v6.image.Option;
import com.wix.mediaplatform.v6.image.encoder.JPEG;
import com.wix.mediaplatform.v6.image.filter.*;
import com.wix.mediaplatform.v6.image.framing.Crop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.wix.mediaplatform.v6.image.Metadata.KEY_MIME_TYPE;
import static com.wix.mediaplatform.v6.image.StringToken.*;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class ImageUrlParser {

    public static final Map<String, Class> OPTIONS = ImmutableMap.<String, Class>builder()
            .put(Blur.KEY, Blur.class)
            .put(Brightness.KEY, Brightness.class)
            .put(Contrast.KEY, Contrast.class)
            .put(JPEG.KEY, JPEG.class)
            .put(Hue.KEY, Hue.class)
            .put(Saturation.KEY, Saturation.class)
            .put(UnsharpMask.KEY, UnsharpMask.class)
            .build();

    public static void parse(Image image, String url) {
        ExplodedUrl explodedUrl;
        try {
            explodedUrl = new ExplodedUrl(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("not a valid image url", e);
        }

        image.setFileName(explodedUrl.getFileName());
        image.setPath(explodedUrl.getPath());
        image.setHost(explodedUrl.getHost());
        image.setMetadata(parseFragment(explodedUrl.getFragment()));

        applyOptions(image, explodedUrl.getGeometry(), explodedUrl.getOptions());
    }

    private static Metadata parseFragment(String fragment) {
        if (fragment == null) {
            return null;
        }

        Map<String, String> values = newHashMap();
        String[] parts = fragment.split(COMMA);
        for (String part : parts) {
            String[] params = part.split(UNDERSCORE);
            if (params.length < 2) {
                continue;
            }
            values.put(params[0], params[1]);
        }

        if (values.get(KEY_WIDTH) == null || values.get(KEY_HEIGHT) == null || values.get(KEY_MIME_TYPE) == null) {
            return null;
        }

        return new Metadata(parseInt(values.get(KEY_WIDTH)), parseInt(values.get(KEY_HEIGHT)), values.get(KEY_MIME_TYPE));
    }

    private static void applyOptions(Image image, String geometry, String options) {
        Map<String, String[]> params = newHashMap();
        String[] parts = options.split(COMMA);
        for (String part : parts) {
            String[] param = part.split(UNDERSCORE);
            params.put(param[0], Arrays.copyOfRange(param, 1, param.length));
        }

        Method method = findMethod(image, geometry);
        if (method == null) {
            throw new IllegalArgumentException("geometry not supported");
        }

        try {
            switch (method.getName()) {
                case "crop":
                    method.invoke(
                            image,
                            parseInt(params.get(KEY_WIDTH)[0]),
                            parseInt(params.get(KEY_HEIGHT)[0]),
                            parseInt(params.get(Crop.KEY_X)[0]),
                            parseInt(params.get(Crop.KEY_Y)[0]),
                            parseFloat(params.get(Crop.KEY_SCALE)[0])
                    );
                    params.remove(KEY_WIDTH);
                    params.remove(KEY_HEIGHT);
                    params.remove(Crop.KEY_X);
                    params.remove(Crop.KEY_Y);
                    params.remove(Crop.KEY_SCALE);
                    break;
                default:
                    method.invoke(image, parseInt(params.get(KEY_WIDTH)[0]), parseInt(params.get(KEY_HEIGHT)[0]));
                    params.remove(KEY_WIDTH);
                    params.remove(KEY_HEIGHT);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("geometry not supported");
        }

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            try {
                Option option = (Option) OPTIONS.get(param.getKey()).newInstance();
                option.deserialize(param.getValue());
                image.addOption(option);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new IllegalArgumentException("option not supported");
            }
        }
    }

    private static Method findMethod(Image image, String methodName) {
        if ("scrop".equals(methodName)) {
            methodName = "smartCrop";
        }
        Method[] methods = image.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        return null;
    }

    private static class ExplodedUrl {
        
        private static final Joiner joiner = Joiner.on(FORWARD_SLASH).skipNulls();

        private String host;

        private String path;

        private String version;

        private String geometry;

        private String options;

        private String fileName;

        private String fragment;

        ExplodedUrl(String url) throws URISyntaxException {
            URI uri = new URI(url);

            this.host = (uri.getScheme() != null ? uri.getScheme() + "://" : "//") + uri.getHost() + (uri.getPort() != -1 ? ":" + uri.getPort() : "") + "/";
            this.fragment = uri.getFragment();

            String[] parts = uri.getPath().split(FORWARD_SLASH);
            this.fileName = parts[parts.length - 1];
            this.options = parts[parts.length - 2];
            this.geometry = parts[parts.length - 3];
            this.version = parts[parts.length - 4];
            this.path = joiner.join(Arrays.copyOfRange(parts, 0, parts.length - 4));
        }

        String getHost() {
            return host;
        }

        String getPath() {
            return path;
        }

        String getVersion() {
            return version;
        }

        String getGeometry() {
            return geometry;
        }

        String getOptions() {
            return options;
        }

        String getFileName() {
            return fileName;
        }

        String getFragment() {
            return fragment;
        }
    }
}
