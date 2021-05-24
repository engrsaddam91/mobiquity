package com.mobiquity.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@EqualsAndHashCode
public final class QualifiedItems {
	@Getter
	private final String id;
	private final List<Item> products;

	public List<Item> getProducts() {
		if (Objects.isNull(products))
			return null;
		if (products.isEmpty())
			return new ArrayList<>();
		return products.stream()
				.map(prod -> new Item(prod.getIndex(), prod.getWeight(), prod.getCost()))
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		if (Objects.isNull(products) || products.isEmpty())
			return "-";
		StringBuilder sb = new StringBuilder();
		products.forEach(product -> sb.append(product.getIndex()).append(","));
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}
