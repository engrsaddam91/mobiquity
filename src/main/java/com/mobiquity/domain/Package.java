package com.mobiquity.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public final class Package {
    @Getter
    private final String ID = UUID.randomUUID().toString();
    @Getter
    private final double weightLimit;
    private final List<Item> items;

    public List<Item> getItems() {
        if (Objects.isNull(items))
            return null;
        if (items.isEmpty())
            return new ArrayList<>();
        return items.stream()
                .map(item -> new Item(item.getIndex(), item.getWeight(), item.getCost()))
                .collect(Collectors.toList());
    }
}
