package com.mobiquity.packer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobiquity.exception.APIException;

@SpringBootTest
public class PackerTest {
	
	private static final String VALID_FILE_PATH = "src/test/resources/example_input";
	private static final String INVALID_FILE_PATH = "src/test/resources/wrong_input";
	
	@Autowired
	private Packer packer;

	@Test
	public void testPack() throws APIException{
		String packages = packer.pack(VALID_FILE_PATH);
		assertNotNull(packages);
		assertEquals(getExpectedResult(), packages);

	}

	@Test
	public void testPackInvalidFilePath_shouldThrowException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> packer.pack(INVALID_FILE_PATH));
	}

	@Test
	public void testPackFilePathIsEmpty_shouldThrowException() throws APIException {
		Assertions.assertThrows(APIException.class, () -> packer.pack(null));
	}

	private String getExpectedResult() {
		return "4\n-\n2,7\n8,9";
	}

}
