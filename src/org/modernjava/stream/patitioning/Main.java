package org.modernjava.stream.patitioning;

import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		final Map<Boolean, List<Integer>> compositesWithCollector = PartitioningMethods.partitioningCompositesWithCollector(90);
		System.out.println(compositesWithCollector);
	}
}
