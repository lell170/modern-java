package org.java.modernjava.stream.spliterator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Measurement(iterations = 5)
@State(value = Scope.Benchmark)
public class WordCounterPerformance {

	private SpliteratorMethods spliteratorMethods;

	@Setup
	public void preSetup() {
		spliteratorMethods = new SpliteratorMethods();
	}

	@Benchmark
	public int countWordsForStream() {
		return spliteratorMethods.streamMethod();
	}

	@Benchmark
	public int countWordsIteratively() {
		return spliteratorMethods.countWordsIteratively();
	}

	@Benchmark
	public int countWordsForStreamParallelCorrect() {
		return spliteratorMethods.correctParallelStreamMethod();
	}

	@TearDown(Level.Invocation)
	public void tearDown() {
		System.gc();
	}

}
