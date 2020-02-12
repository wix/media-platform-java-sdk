package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.service.Specification;

public class CreateUrlSetOperation extends Operation {

    private UrlSet extraResults;

    @Override
    public Specification getSpecification() {
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
