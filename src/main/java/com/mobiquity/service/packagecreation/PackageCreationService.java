package com.mobiquity.service.packagecreation;

import java.util.List;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;

public interface PackageCreationService {
    String createPackages(List<Package> packages) throws APIException;
    void clear();

}
