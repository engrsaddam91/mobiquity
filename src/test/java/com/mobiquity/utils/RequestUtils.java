package com.mobiquity.utils;

import java.util.List;

public class RequestUtils {

	public static List<String> getInValidFileData() {
		return List.of("81 (1,53.38,$45) (2,88.62,$98)", "8 : (1,15.3,$34)");
	}

	public static List<String> getInvalidItemData() {
		return List.of("81 : (1,53.38$45) (88.62,$98)", "8 : (1,15.3$34)");
	}

	public static List<String> getInvalidItemWeight() {
		return List.of("81 : (1,hfhf,$45) (88.62,$98)", "8 : (1,15.3,$34)");
	}

	public static List<String> getInvalidItemCost() {
		return List.of("81 : (1,50.60,gghgf) (88.62,$98)", "8 : (1,15.3,$34)");
	}
}
