package org.modernjava.stream.patitioning;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitioningMethods {

	public boolean isComposite(int number) {
		return IntStream.range(2, number)
				.anyMatch(value -> number % value == 0);

	}

	public Map<Boolean, List<Integer>> partitionCompositeNumbers(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.parallel()
				.collect(Collectors.partitioningBy(this::isComposite));
	}


	public static void main(String[] args) {
		PartitioningMethods partitioningMethods = new PartitioningMethods();
		System.out.println(partitioningMethods.isComposite(35));
		System.out.println(partitioningMethods.partitionCompositeNumbers(155));
	}
}
