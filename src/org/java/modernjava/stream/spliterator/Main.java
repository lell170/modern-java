package org.java.modernjava.stream.spliterator;

public class Main {

	public static void main(String[] args) {
		SpliteratorMethods spliteratorMethods = new SpliteratorMethods();

		System.out.println(spliteratorMethods.streamMethod());
		System.out.println(spliteratorMethods.wrongParallelStreamMethod());
		System.out.println(spliteratorMethods.countWordsIteratively());
		System.out.println(spliteratorMethods.correctParallelStreamMethod());
	}
}
