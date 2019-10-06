package org.java.modernjava.stream.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Measurement(iterations = 2)
@Warmup(iterations = 2)
@State(value = Scope.Benchmark)
public class ParallelStreamPerformance {

	private static final long N = 10_000_000L;

	@Benchmark
	public long normalStreamMethod() {
		return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
	}

	@Benchmark
	public long forLoopMethod() {
		long result = 0;
		for (long i = 1L; i <= N; i++) {
			result += i;
		}
		return result;
	}

	@Benchmark
	public long numericStreamRange() {
		return LongStream.rangeClosed(1, N)
				.reduce(0L, Long::sum);
	}

	// numeric parallel stream is faster than all another methods to calculate sum of digits.
	@Benchmark
	public long parallelNumericStreamRange() {
		return LongStream.rangeClosed(1, N)
				.parallel()
				.reduce(0L, Long::sum);
	}

	@TearDown(Level.Invocation)
	public void tearDown() {
		System.gc();
	}

}
