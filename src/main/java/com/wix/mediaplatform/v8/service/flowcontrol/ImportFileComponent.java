package com.wix.mediaplatform.v8.service.flowcontrol;

import com.wix.mediaplatform.v8.service.file.ImportFileSpecification;

public class ImportFileComponent extends Component {

    private ImportFileSpecification specification;

    @Override
    public ImportFileSpecification getSpecification() {
        return specification;
    }

    public ImportFileComponent setSpecification(ImportFileSpecification specification) {
        this.specification = specification;
        return this;
    }
}
