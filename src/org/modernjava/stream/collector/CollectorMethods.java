package org.modernjava.stream.collector;

import org.modernjava.testdata.transactions.Currency;
import org.modernjava.testdata.transactions.Transaction;

import java.util.*;

import static java.util.stream.Collectors.*;

public class CollectorMethods {

    // this method is only for learning collect stream method. Not useless for production
    // could be useful by using with other collectors
    static Long getCountOfAllTransactions(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(counting());
    }

    // also can be replaced with IntStream sum method
    static int getSumOfAllTransactionValues(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(summingInt(Transaction::getSalesValue));
        // another method: averaging...
    }

    // max transaction value by comparator
    // can be replaced with max method of stream
    static Transaction getTransactionWithHighestValue(List<Transaction> transactionList) {
        Comparator<Transaction> transactionValueComparator = Comparator.comparingInt(Transaction::getSalesValue);

        return transactionList.stream().
                collect(maxBy(transactionValueComparator)).get();
    }

    // statistics about whole transaction values (average, min, max etc.)
    static IntSummaryStatistics getTransactionStatistics(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(summarizingInt(Transaction::getSalesValue));
    }

    // use reducing method from Collectors. Return value is Optional
    // in same cases is better to use reducing instead of reduce Method of stream
    // collect is mutable reduction. Is better for parallel processing
    // collect() can only work with mutable result objects.
    // reduce() is designed to work with immutable result objects. reduce is bad if objects are mutable
    static Transaction getTransactionWithHighestValueByReducing(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(reducing((d1, d2) -> d1.getSalesValue() > d2.getSalesValue() ? d1 : d2)).get();
    }

    // grouping by example
    static Map<Currency, Optional<Transaction>> getMapHighestValueByCurrencyOptional(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(groupingBy(Transaction::getCurrency, maxBy(Comparator.comparingInt(Transaction::getSalesValue))));
    }

    // same example without optional
    static Map<Currency, Transaction> getMapHighestValueByCurrency(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(groupingBy(Transaction::getCurrency, collectingAndThen(maxBy(Comparator.comparingInt(Transaction::getSalesValue)), Optional::get)));
    }

    static Map<Currency, List<Transaction>> getTransactionsByCurrencies(List<Transaction> transactionList) {
        return transactionList.stream().
                collect(groupingBy(Transaction::getCurrency));
    }

}
