package com.mobiquity.service.filereader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.filereader.Impl.TextFileReaderService;

@SpringBootTest
public class FileReaderServiceTest {

	private static final String VALID_FILE_PATH = "src/test/resources/example_input";
	private static final String INCORRECT_FILE_PATH = "src/test/resources/wrong_input";

	@Autowired
	private TextFileReaderService underTest;

	@Test
	public void testReadFile_shouldReturnListOfLines() throws APIException {
		List<String> fileData = underTest.readFile(VALID_FILE_PATH);
		assertNotNull(fileData);
		assertEquals(4, fileData.size());
	}

	@Test
	public void testReadFileInvalidPath_shouldThrowException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> underTest.readFile(INCORRECT_FILE_PATH));
	}

	@Test
	public void testReadFilePathIsEmpty_shouldThrowException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> underTest.readFile(null));
	}

}
