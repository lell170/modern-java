package modernjava.stream.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

class WordCounterSpliterator implements Spliterator<Character> {

	private final String text;
	private int currentChar = 0;

	public WordCounterSpliterator(final String text) {
		this.text = text;
	}

	@Override
	public boolean tryAdvance(final Consumer<? super Character> action) {
		action.accept(text.charAt(currentChar++));
		return currentChar < text.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		final int currentSize = text.length() - currentChar;
		if (currentSize < 10) {
			return null;
		}
		for (int splitPos = currentSize / 2 + currentChar; splitPos < text.length(); splitPos++) {
			if (Character.isWhitespace(text.charAt(splitPos))) {
				final Spliterator<Character> spliterator =
						new WordCounterSpliterator(text.substring(currentChar, splitPos));
				currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return text.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
}
