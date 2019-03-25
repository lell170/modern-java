package org.modernjava.stream.patitioning;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitioningMethods {

	public static boolean isComposite(int number) {
		return IntStream.range(2, number).anyMatch(value -> number % value == 0);
	}

	public static Map<Boolean, List<Integer>> partitionCompositeNumbers(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.parallel()
				.collect(Collectors.partitioningBy(PartitioningMethods::isComposite));
	}

	// partitioning with collector
	public static Map<Boolean, List<Integer>> partitioningCompositesWithCollector(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(new CompositeNumbersCollector());
	}

}
