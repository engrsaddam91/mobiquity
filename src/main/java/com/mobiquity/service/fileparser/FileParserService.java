package com.mobiquity.service.fileparser;
import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;

import java.util.List;
public interface FileParserService {
    List<Package> parseFileData(List<String> data) throws APIException;
}
