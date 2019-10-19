package modernjava.stream.spliterator;

class Main {

	public static void main(final String[] args) {
		final SpliteratorMethods spliteratorMethods = new SpliteratorMethods();

		System.out.println(spliteratorMethods.streamMethod());
		System.out.println(spliteratorMethods.wrongParallelStreamMethod());
		System.out.println(spliteratorMethods.countWordsIteratively());
		System.out.println(spliteratorMethods.correctParallelStreamMethod());
	}
}
