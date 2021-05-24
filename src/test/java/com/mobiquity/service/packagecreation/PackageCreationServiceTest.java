package com.mobiquity.service.packagecreation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.fileparser.Impl.FileParserServiceImpl;
import com.mobiquity.service.filereader.Impl.TextFileReaderService;
import com.mobiquity.utils.Constants;

@SpringBootTest
public class PackageCreationServiceTest {

	@Autowired
	private PackageCreationService packageService;
	@Autowired
	private TextFileReaderService fileReaderService;
	@Autowired
	private FileParserServiceImpl fileParserService;

	@Test
	public void testCreatePackages_Success() throws APIException{
		List<Package> packages = fileParserService
				.parseFileData(fileReaderService.readFile(Constants.EXAMPLE_INPUT_FILE_PATH));
		String actualResult = packageService.createPackages(packages);
		assertNotNull(actualResult);
		assertEquals(getExpectedResult(), actualResult);
	}

	@Test
	public void testCreatePackages_WhenPackagesAreEmpty_throwException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> packageService.createPackages(null));
	}
	
	@BeforeEach
	public void clearTestData() {
		packageService.clear();
	}


	private String getExpectedResult() {
		return "4\n-\n2,7\n8,9";
	}
	
	

}
