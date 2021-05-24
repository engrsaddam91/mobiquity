package com.mobiquity.packer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.fileparser.FileParserService;
import com.mobiquity.service.filereader.FileReaderService;
import com.mobiquity.service.packagecreation.PackageCreationService;

@Component
public class Packer {

  @Autowired
  private PackageCreationService packageService;

  @Autowired
  private FileParserService fileParserService;

  @Autowired
  private FileReaderService fileReaderService;

  public String pack(String filePath) throws APIException {
    List<Package> packages = fileParserService.parseFileData(fileReaderService.readFile(filePath));
    return packageService.createPackages(packages);
  }
}
