package com.mobiquity.service.packagecreation.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Package;
import com.mobiquity.domain.QualifiedItems;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.packagecreation.PackageCreationService;

@Service
public class PackageCreationServiceImpl implements PackageCreationService {
	
	private Map<Package, Queue<Item>> packageItems = new LinkedHashMap<>();
	private List<QualifiedItems> qualifiedPackageItems = new ArrayList<>();

	/**
     * This Method Create the Package of Qualified Items
     * @param List of packages
     * @return String of QualifiedItems
     * @throws APIException if there is any issue with createPackages
     */
	@Override
	public String createPackages(List<Package> packages) throws APIException {
		if(Objects.isNull(packages) || packages.isEmpty() ) {
			throw new APIException("No Items Exist in Provided Packages");
		}
		initPackageMaxHeap(packages);
		getQualifiedItems();
		return qualifiedPackageItems.stream()
                .map(QualifiedItems::toString)
                .collect(Collectors.joining("\n"));
	}

	private void initPackageMaxHeap(List<Package> packages) {
		packages.forEach(pkg -> {
			packageItems.put(pkg, initMaxHeap());
			packageItems.get(pkg).addAll(pkg.getItems());
		});
	}

	private PriorityQueue<Item> initMaxHeap() {
		return new PriorityQueue<>((item1, item2) -> {
			int costComparison = Double.compare(item2.getCost(), item1.getCost());
			int weightComparison = (costComparison == 0) ? Double.compare(item1.getWeight(), item2.getWeight())
					: costComparison > 0 ? 1 : -1;
			return weightComparison;
		});
	}

	private void getQualifiedItems() {
		for (Package key : packageItems.keySet()) {
			double packageWeight = key.getWeightLimit();
			List<Item> items = new ArrayList<>();
			Queue<Item> itemQueue = packageItems.get(key);
			while (!itemQueue.isEmpty()) {
				Item item = itemQueue.poll();
				if (packageWeight >= item.getWeight()) {
					packageWeight = packageWeight - item.getWeight();
					items.add(item);
				}
			}
			qualifiedPackageItems.add(new QualifiedItems(key.getID(), items));
		}
	}
	
	@Override
	public void clear() {
		packageItems.clear();
		qualifiedPackageItems.clear();
	}
	
}
