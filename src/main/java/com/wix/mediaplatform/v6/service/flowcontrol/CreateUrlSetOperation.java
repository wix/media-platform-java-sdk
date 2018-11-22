package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.service.file.ImportFileSpecification;

public class CreateUrlSetOperation extends Operation {

    private UrlSet extraResults;

    @Override
    public ImportFileSpecification getSpecification() {
        return null;
    }

    @Override
    public UrlSet getExtraResults() {
        return null;
    }

    public class UrlSet implements ExtraResults {

        private String urlset;

        public UrlSet() {
        }

        public String getUrlset() {
            return urlset;
        }

        public UrlSet setUrlset(String urlset) {
            this.urlset = urlset;
            return this;
        }
    }
}
