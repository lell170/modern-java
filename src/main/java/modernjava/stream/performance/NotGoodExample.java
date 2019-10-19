package modernjava.stream.performance;

import static java.util.stream.Collector.Characteristics.CONCURRENT;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.LongStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Measurement(iterations = 2)
@Warmup(iterations = 2)
@State(value = Scope.Benchmark)
class NotGoodExample {

	private static final long N = 10_000_000L;

	@Benchmark
	public long sideEffectSum() {
		final Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, N).forEach(accumulator::add);
		System.out.println("sideEffectSum " + accumulator.total);
		return accumulator.total;
	}

	@Benchmark
	// this will return incorrect results!
	public long sideEffectParallelSum() {
		final Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, N).parallel().forEach(accumulator::add);
		System.out.println("sideEffectParallelSum with reduce" + accumulator.total);
		return accumulator.total;
	}

	@Benchmark
	// this will return correct results cause of collector
	public long sideEffectParallelSumWithCollect() {
		final Accumulator accumulator = LongStream.rangeClosed(1, N).parallel().boxed()
                                                  .collect(Collector.of(Accumulator::new, Accumulator::add, (acc1, acc2) -> {
					acc1.add(acc2.total);
					return acc1;
				}, t->t, CONCURRENT));
		System.out.println("sideEffectParallelSum with collect " + accumulator.total);
		return accumulator.total;
	}

	@Benchmark
	// this will return correct results cause of collector
	public long sideEffectSingleStreamWithCollect() {
		final Accumulator accumulator = LongStream.rangeClosed(1, N).boxed()
                                                  .collect(Collector.of(Accumulator::new, Accumulator::add, (acc1, acc2) -> {
					acc1.add(acc2.total);
					return acc1;
				}, t->t, CONCURRENT));
		System.out.println("side effect single with collect " + accumulator.total);
		return accumulator.total;
	}


	class Accumulator {

		long total = 0;

		void add(final long value) {
			total += value;
		}

		public void combine(final Accumulator accumulator1, final Accumulator accumulator2) {
			this.add(accumulator1.total + accumulator2.total);
		}
	}
}
