package com.mobiquity.service.filereader;
import java.util.List;

import com.mobiquity.exception.APIException;
public interface FileReaderService {
    List<String> readFile(String filePath) throws APIException;
}
