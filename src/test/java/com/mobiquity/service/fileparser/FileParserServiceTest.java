package com.mobiquity.service.fileparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.service.filereader.Impl.TextFileReaderService;
import com.mobiquity.utils.Constants;
import com.mobiquity.utils.RequestUtils;

@SpringBootTest
public class FileParserServiceTest {

	@Autowired
	private FileParserService underTest;

	@Autowired
	private TextFileReaderService fileReaderService;

	@Test
	public void testParseFileData_Success() throws IOException, APIException {
		List<String> fileData = fileReaderService.readFile(Constants.EXAMPLE_INPUT_FILE_PATH);
		List<Package> actual = underTest.parseFileData(fileData);
		assertNotNull(actual);
		assertEquals(fileData.size(), actual.size());
	}

	@Test
	public void testParseFileData_whenDataIsEmpty_thowException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> underTest.parseFileData(null));
	}

	@Test
	public void testParseFileData_whenInvalidDataProvided_thowException() throws APIException {
		Assertions.assertThrows(FileParserException.class,
				() -> underTest.parseFileData(RequestUtils.getInValidFileData()));
	}

	@Test
	public void testParseFileData_whenInvalidItemProvided_thowException() throws APIException {
		Assertions.assertThrows(FileParserException.class,
				() -> underTest.parseFileData(RequestUtils.getInvalidItemData()));
	}

	@Test
	public void testParseFileData_whenInvalidItemWeightProvided_thowException() throws APIException {
		Assertions.assertThrows(FileParserException.class,
				() -> underTest.parseFileData(RequestUtils.getInvalidItemWeight()));
	}

	@Test
	public void testParseFileData_whenInvalidItemCostProvided_thowException() throws APIException {
		Assertions.assertThrows(FileParserException.class,
				() -> underTest.parseFileData(RequestUtils.getInvalidItemCost()));
	}

}
