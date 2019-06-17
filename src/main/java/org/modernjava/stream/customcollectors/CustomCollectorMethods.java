package org.modernjava.stream.customcollectors;

import org.modernjava.testdata.transactions.Transaction;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class CustomCollectorMethods {

    // Collector.of() suggestion
    // (it all can be replaced with lambda or method reference)
    static List<String> getNamesOfAllCitiesCollectorOf(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .map(transaction -> transaction.getCustomer().getCity()).distinct()
                .collect(Collector.of(
                        // Container: Supplier returns new Object Typ T without parameter
                        new Supplier<List<String>>() {
                            @Override
                            public List<String> get() {
                                return new ArrayList<>();
                            }
                        },
                        // Accumulator: BiConsumer process operation with two given arguments of type T and U
                        new BiConsumer<List<String>, String>() {
                            @Override
                            public void accept(List<String> list, String item) {
                                list.add(item);
                            }
                        },
                        // Combiner: BinaryOperator obtains two parameter same Type and returns the same Type as given <T,T,T>
                        new BinaryOperator<List<String>>() {
                            @Override
                            public List<String> apply(List<String> list1, List<String> list2) {
                                list1.addAll(list2);
                                return list1;
                            }
                        },
                        // Finisher for end of accumulation process. here all will be converted to UPPER CASE
                        t -> {
                            t.replaceAll(String::toUpperCase);
                            return t;
                        },
                        // Characteristics of collect.
                        // CONCURRENT -> for parallel processing. Only if stream are unordered
                        // IDENTYTY_FINISH -> accumulator object can be used for end collect process without finisher()
                        // UNORDERED -> the result is not affected by the order of stream.
                        CONCURRENT, UNORDERED));

    }

    // Custom collector class suggestion
    public static List<String> getNamesOfAllCitiesWithCustomCollector(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .map(transaction -> transaction.getCustomer().getCity())
                .distinct()
                .collect(new ToListCollector<>());
    }

    // Custom collector class implementation
    private static class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        public Set<Collector.Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
        }
    }

    // List is mutable Container. Can be used again to append new elements.
    // stream.reduce() creates new objects on each step of reduction
    static List<String> getNamesOfAllCustomers(List<Transaction> transactionList) {
        return transactionList
                .stream().map(transaction -> transaction.getCustomer().getName())
                .collect(() -> new ArrayList<>(), (list, item) -> list.add(item), (list, items) -> list.addAll(items));
    }

    // throws UnsupportedOperationException because immutable Data Structure is given.
    // Unmodifiable List implements List but all mutable operations throws Exception.
    static List<String> produceExceptionByWrongUseOfCollect(List<Transaction> transactionList) {
        List<String> emptyList = Collections.emptyList();
        try {
            return transactionList
                    .stream().map(transaction -> transaction.getCustomer().getName())
                    .collect(() -> Collections.unmodifiableList(emptyList), List::add, List::addAll);
        } catch (UnsupportedOperationException e) {
            System.out.println("cant process it. Immutable data structure is given");
            return emptyList;
        }
    }
}
