package org.java.org.modernjava.stream.spliterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("WeakerAccess")
public class SpliteratorMethods {

	public String testString =
			"Jemand mußte Josef K. verleumdet haben, denn ohne daß er etwas Böses getan hätte, wurde ereines Morgens " +
					"verhaftet. Die Köchin der Frau Grubach, seiner Zimmervermieterin, die ihm jedenTag gegen acht Uhr" +
					" früh das Frühstück brachte, kam diesmal nicht.";

	public SpliteratorMethods() {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("text.txt"))) {
			while ((br.readLine()) != null) {
				stringBuilder.append(br.readLine());
			}
			testString = stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// iteratively method
	public int countWordsIteratively() {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : this.testString.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}

	public int calculateWordsFromStream(Stream<Character> stream) {
		WordCounter wordCounter =
				stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		return wordCounter.getCounter();
	}


	public int streamMethod() {
		Stream<Character> stream = IntStream.range(0, this.testString.length())
				.mapToObj(this.testString::charAt);

		return calculateWordsFromStream(stream);
	}

	// this method split string arbitrary. This method will return false result!
	// solution is to make sure that the string is separated only after each word by parallel processing!
	public int wrongParallelStreamMethod() {
		Stream<Character> stream = IntStream.range(0, this.testString.length())
				.mapToObj(this.testString::charAt).parallel();

		return calculateWordsFromStream(stream);
	}

	public int correctParallelStreamMethod() {
		Spliterator<Character> spliterator = new WordCounterSpliterator(this.testString);
		Stream<Character> stream = StreamSupport.stream(spliterator, true);

		return calculateWordsFromStream(stream);

	}
}
