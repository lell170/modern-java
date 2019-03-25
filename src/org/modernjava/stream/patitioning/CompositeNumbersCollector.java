package org.modernjava.stream.patitioning;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static org.modernjava.stream.patitioning.PartitioningMethods.isComposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CompositeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		// can be replaced by anonymous class
		return () -> {
			HashMap<Boolean, List<Integer>> booleanListHashMap = new HashMap<>();
			booleanListHashMap.put(true, new ArrayList<>());
			booleanListHashMap.put(false, new ArrayList<>());
			return booleanListHashMap;
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> accumulator, Integer candidate) -> {
			accumulator.get(isComposite(candidate)).add(candidate);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
			map1.get(true)
					.addAll(map2.get(true));
			map1.get(false)
					.addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return i -> i;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

}