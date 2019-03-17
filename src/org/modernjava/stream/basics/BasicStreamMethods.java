package org.modernjava.stream.basics;

import org.modernjava.testdata.transactions.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Static Methods for stream examples with Transactions (package testdata.transactions) Lists
public class BasicStreamMethods {

    static List<Transaction> findAllByYearAndSort(List<Transaction> transactionList, int year) {

        return transactionList
                .stream()
                .filter(transaction -> transaction.getYear() == year)
                .sorted(Comparator.comparing(Transaction::getSalesValue))
                .collect(Collectors.toList());
    }

    static List<Transaction> getAllForUniqueCityNames(List<Transaction> transactionList) {

        return transactionList
                .stream()
                .filter(distinctByKey(transaction -> transaction
                        .getCustomer()
                        .getCity()))
                .collect(Collectors.toList());
    }

    static List<Transaction> allCustomersForGivenCity(List<Transaction> transactionList, String city) {

        return transactionList
                .stream()
                .filter(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city))
                .distinct()
                .collect(Collectors.toList());
    }

    static String allCustomerNames(List<Transaction> transactionList) {

        return transactionList
                .stream()
                .map(transaction -> transaction
                        .getCustomer()
                        .getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining(", "));
    }

    static boolean areAnyCustomersFromGivenCity(List<Transaction> transactionList, String city) {

        return transactionList
                .stream()
                .anyMatch(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city));
    }

    static String getAllSalesValuesForGivenCity(List<Transaction> transactionList, String city) {

        return transactionList
                .stream()
                .filter(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city))
                .map(Transaction::getSalesValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    static Integer getHighestValue(List<Transaction> transactionList) {
        // The problem with this code is that there are a lot of boxing operations. Behind the scenes
        // each Integer needs to be unboxed to a primitive before performing the summation. Better solution IntStream.*
        return transactionList
                .stream()
                .map(Transaction::getSalesValue)
                .reduce(Integer::max)
                .orElse(0);
    }

    static Transaction getTransactionWithSmalestValue(List<Transaction> transactionList) {

        // return transactions.stream().reduce(getSmallest()).orElse(null);
        return transactionList
                .stream()
                .min(Comparator.comparing(Transaction::getSalesValue))
                .orElse(null);
    }

    // distinct by Key for Stream.
    // https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();

        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
