package com.mobiquity.service.fileparser.Impl;

import com.mobiquity.domain.Package;
import com.mobiquity.domain.Item;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.service.fileparser.FileParserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mobiquity.utils.Constants.*;

@Service
public class FileParserServiceImpl implements FileParserService {

	/**
     * This Method Gets File Data and Parse it to Packages List
     * @param data , file data
     * @return List of Packages
     * @throws APIException if there is any issue with File Parsing
     */
	@Override
	public List<Package> parseFileData(List<String> data) throws APIException {
		if (Objects.isNull(data) || data.isEmpty())
			throw new APIException("Data for file parser is empty");

		return data.stream().map(this::parsePackage).collect(Collectors.toList());
	}

	private Package parsePackage(String data) {
		String[] weightToItems = data.split(WEIGHT_SPLIT_DELIMITER);
		if (weightToItems.length != 2) {
			throw new FileParserException("Line must contain exactly one ':'");
		}
		try {
			double weight = Double.parseDouble(weightToItems[0]);
			String[] itemsData = weightToItems[1].split(ITEM_SPLIT_PATTERN);
			List<Item> items = Arrays.stream(itemsData)
											.map(this::parseItem)
											.filter(Objects::nonNull)
											.collect(Collectors.toList());

			if (items.size() > MAX_ITEM_LIMIT) {
				items = items.subList(0, MAX_ITEM_LIMIT);
			}

			return new Package(weight, items);
		} catch (NumberFormatException e) {
			throw new FileParserException("Invalid format for weight");
		}
	}

	private Item parseItem(String item) {
		if (item.isBlank()) {
			return null;
		}

		String[] itemSplit = item.split(ITEM_SPLIT_DELIMITER);
		if (itemSplit.length < 3) {
			throw new FileParserException("Invalid format for Item");
		}
		try {
			int index = Integer.parseInt(itemSplit[0]);
			double weight = Double.parseDouble(itemSplit[1]);
			double cost = Double.parseDouble(itemSplit[2].substring(1));
			if (weight > MAX_WEIGHT_LIMIT)
				return null;

			return new Item(index, weight, cost);
		} catch (NumberFormatException e) {
			throw new FileParserException("Invalid format for Item attribute", e);
		}
	}

}
