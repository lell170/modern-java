package modernjava.stream.basics;

import modernjava.testdata.transactions.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Static Methods for stream examples with Transactions (package testdata.transactions) Lists
class BasicStreamMethods {

    static List<Transaction> findAllByYearAndSort(final List<Transaction> transactionList) {

        return transactionList
                .stream()
                .filter(transaction -> transaction.getYear() == 2019)
                .sorted(Comparator.comparing(Transaction::getSalesValue))
                .collect(Collectors.toList());
    }

    static List<Transaction> getAllForUniqueCityNames(final List<Transaction> transactionList) {

        return transactionList
                .stream()
                .filter(distinctByKey(transaction -> transaction
                        .getCustomer()
                        .getCity()))
                .collect(Collectors.toList());
    }

    static List<Transaction> allCustomersForGivenCity(final List<Transaction> transactionList, final String city) {

        return transactionList
                .stream()
                .filter(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city))
                .distinct()
                .collect(Collectors.toList());
    }

    static String allCustomerNames(final List<Transaction> transactionList) {

        return transactionList
                .stream()
                .map(transaction -> transaction
                        .getCustomer()
                        .getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining(", "));
    }

    static boolean areAnyCustomersFromGivenCity(final List<Transaction> transactionList, final String city) {

        return transactionList
                .stream()
                .anyMatch(transaction -> transaction
                        .getCustomer()
                        .getCity()
                        .equals(city));
    }

    static String getAllSalesValuesForGivenCity(final List<Transaction> transactionList, final String city) {

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

    static Integer getHighestValue(final List<Transaction> transactionList) {
        // The problem with this code is that there are a lot of boxing operations. Behind the scenes
        // each Integer needs to be unboxed to a primitive before performing the summation. Better solution would be IntStream.*
        return transactionList
                .stream()
                .map(Transaction::getSalesValue)
                .reduce(Integer::max)
                .orElse(0);
    }

    static Transaction getTransactionWithSmalestValue(final List<Transaction> transactionList) {

        // return transactions.stream().reduce(getSmallest()).orElse(null);
        return transactionList
                .stream()
                .min(Comparator.comparing(Transaction::getSalesValue))
                .orElse(null);
    }

    // distinct by Key for Stream.
    // https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
    private static <T> Predicate<T> distinctByKey(final Function<? super T, Object> keyExtractor) {
        final Map<Object, Boolean> map = new ConcurrentHashMap<>();

        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    // ÖBB Train Number Check
    static void generateCheckSumFromTrainNumber(final String number) {
        //TODO: implement next part
        final List<Integer> listBeforeLine = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        // Atomic integer for incrementing functions... not the best solution
        final AtomicInteger atomicInteger = new AtomicInteger();
        final int checkSum = listBeforeLine.stream().mapToInt(Integer::intValue).map(raw -> raw * ((1 + atomicInteger.getAndIncrement()) % 2 + 1)).map(multiplied -> {
            if (multiplied >= 10) {
                return (multiplied / 10) + (multiplied % 10);
            } else {
                return multiplied;
            }
        }).sum();
    }
}
