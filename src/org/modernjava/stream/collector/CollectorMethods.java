package org.modernjava.stream.collector;

import org.modernjava.testdata.transactions.Currency;
import org.modernjava.testdata.transactions.Transaction;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.stream.Collectors.*;

// @SuppressWarnings({"SimplifyStreamApiCallChains", "SimplifyCollector"})
public class CollectorMethods {

    // this method is only for learning collect stream method. Not always useless for production
    // could be useful by using with other collectors
    static Long getCountOfAllTransactions(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(counting());
    }

    // also can be replaced with IntStream sum method
    static int getSumOfAllTransactionValues(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(summingInt(Transaction::getSalesValue));
        // another method: averaging...
    }

    // max transaction value by comparator
    // can be replaced with max method of stream
    static Transaction getTransactionWithHighestValue(List<Transaction> transactionList) {
        Comparator<Transaction> transactionValueComparator = Comparator.comparingInt(Transaction::getSalesValue);

        return transactionList
                .stream()
                .collect(maxBy(transactionValueComparator)).get();
    }

    // statistics about whole transaction values (average, min, max etc.)
    static IntSummaryStatistics getTransactionStatistics(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(summarizingInt(Transaction::getSalesValue));
    }

    // use reducing method from Collectors. Return value is Optional
    // in same cases is better to use reducing instead of reduce Method of stream
    // collect is mutable reduction. Is better for parallel processing?
    // collect() can only work with mutable result objects.
    // reduce() is designed to work with immutable result objects. collect is useless if objects are mutable
    static Transaction getTransactionWithHighestValueByReducing(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(reducing((d1, d2) -> d1.getSalesValue() > d2.getSalesValue() ? d1 : d2)).get();
    }

    // grouping by example
    static Map<Currency, Optional<Transaction>> getMapHighestValueByCurrencyOptional(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency, maxBy(Comparator.comparingInt(Transaction::getSalesValue))));
    }

    // same example without optional. can be replaced Collectors.toMap...
    // return transactionList.stream().
    //        collect(Collectors.toMap(Transaction::getCurrency, Function.identity(), BinaryOperator.maxBy(Comparator.comparingInt(Transaction::getSalesValue))));
    static Map<Currency, Transaction> getMapHighestValueByCurrency(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency, collectingAndThen(maxBy(Comparator.comparingInt(Transaction::getSalesValue)), Optional::get)));
    }

    static Map<Currency, List<Transaction>> getTransactionsByCurrencies(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency));
    }

    // custom Collector implementation. Instead of method reference use lambda for understanding purpose

    // () -> new ArrayList<>() -> Supplier. Initializing of Container. Can be replaced by method reference
    // (list, item) -> list.add(item) -> Accumulator
    // (list, items) -> list.addAll(items) Combiner. Is useful for parallel processing.
    // The combiner function must fold the elements from the second result container into the first result container.

    // its all can be replaced by method refernce: collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

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
