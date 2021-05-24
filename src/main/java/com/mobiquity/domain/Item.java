package com.mobiquity.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class Item {
    private final int index;
    private final double weight;
    private final double cost;
}
