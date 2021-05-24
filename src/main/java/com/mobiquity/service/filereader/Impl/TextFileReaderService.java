package com.mobiquity.service.filereader.Impl;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.filereader.FileReaderService;

@Service
public class TextFileReaderService implements FileReaderService {

	/**
     * This Method Gets File Path and Reads Input data
     * @param filePath
     * @return List of String
     * @throws APIException if there is any issue with readFile
     */
	@Override
	public List<String> readFile(String filePath) throws APIException {
		if (Objects.isNull(filePath) || filePath.isBlank()) {
			throw new APIException("File path is not valid.");
		}
		List<String> results = new ArrayList<>();
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))) {
			results = bufferedReader
						.lines()
						.collect(Collectors.toList());
		} catch (Exception e) {
			throw new APIException(e.getMessage(),e);
		}
		return results;
	}
}
