package com.wix.mediaplatform.v7.service.flowcontrol;

import com.wix.mediaplatform.v7.service.file.ImportFileSpecification;

public class ImportFileOperation extends Operation {

    private ImportFileSpecification specification;

    @Override
    public ImportFileSpecification getSpecification() {
        return specification;
    }

    public ImportFileOperation setSpecification(ImportFileSpecification specification) {
        this.specification = specification;
        return this;
    }

    @Override
    public ExtraResults getExtraResults() {
        return null;
    }
}
