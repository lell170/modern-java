package com.artjom.stream.basics;

import com.artjom.testdata.transactions.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Static Methods for stream examples with Transacions (package testdata.transactions) Lists
public class BasicTransactionsMethods {

    static List<Transaction> findAllByYearAndSort(List<Transaction> transactionList, int year) {
        System.out.println("###### all transactionList for year and sort by sales value ######");

        return transactionList
                .stream()
                .filter(transaction -> transaction.getYear() == year)
                .sorted(Comparator.comparing(Transaction::getSalesValue))
                .collect(Collectors.toList());
    }

    static List<Transaction> getAllForUniqueCityNames(List<Transaction> transactionList) {
        System.out.println("###### all transactionList by unique City name ######");

        return transactionList
                .stream()
                .filter(distinctByKey(transaction -> transaction
                        .getCustomer()
                        .getCity()))
                .collect(Collectors.toList());
    }

    static List<Transaction> allCustomersForGivenCity(List<Transaction> transactionList, String city) {
        System.out.println("###### all transactionList from given city ######");

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
        System.out.println("###### all customer names ######");

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
        System.out.println("###### are any customer from given city name? (" + city + ") ######");

        return transactionList
                .stream()
                .anyMatch(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city));
    }

    static String getAllSalesValuesForGivenCity(List<Transaction> transactionList, String city) {
        System.out.println("###### all sales values for given City  (" + city + ")######");

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
        System.out.println("###### Highest Sales Value from all Transactions ######");

        return transactionList
                .stream()
                .map(Transaction::getSalesValue)
                .reduce(Integer::max)
                .orElse(0);
    }

    static Transaction getTransactionWithSmalestValue(List<Transaction> transactionList) {
        System.out.println("###### Smalest Transaction by value ######");

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
