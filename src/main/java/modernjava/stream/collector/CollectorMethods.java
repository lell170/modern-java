package modernjava.stream.collector;

import modernjava.testdata.transactions.Currency;
import modernjava.testdata.transactions.Transaction;

import java.util.*;

import static java.util.stream.Collectors.*;

// @SuppressWarnings({"SimplifyStreamApiCallChains", "SimplifyCollector"})
class CollectorMethods {

    // not always useless for production
    // could be useful by using in combination with other collectors?
    static Long getCountOfAllTransactions(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(counting());
    }

    // also can be replaced with IntStream sum method
    static int getSumOfAllTransactionValues(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(summingInt(Transaction::getSalesValue));
        // another method: averaging...
    }

    // max transaction value by comparator
    // can be replaced with max method of stream
    static Transaction getTransactionWithHighestValue(final List<Transaction> transactionList) {
        final Comparator<Transaction> transactionValueComparator = Comparator.comparingInt(Transaction::getSalesValue);

        return transactionList
                .stream()
                .collect(maxBy(transactionValueComparator)).get();
    }

    // statistics about whole transaction values (average, min, max etc.)
    static IntSummaryStatistics getTransactionStatistics(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(summarizingInt(Transaction::getSalesValue));
    }

    // in same cases is better to use reducing instead of reduce() method of stream.
    // Collect uses mutable container for reduction. for example ArrayList is mutable
    // reduce() is designed to work with immutable result objects. collect is useless if objects are mutable
    // collect is better for parallel processing
    // by simple reducing operations can be replaced with reduce method of Stream API
    static Transaction getTransactionWithHighestValueByReducing(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(reducing((d1, d2) -> d1.getSalesValue() > d2.getSalesValue() ? d1 : d2)).get();
    }

    // grouping by example
    static Map<Currency, Optional<Transaction>> getMapHighestValueByCurrencyOptional(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency, maxBy(Comparator.comparingInt(Transaction::getSalesValue))));
    }

    // same example without optional. can be replaced Collectors.toMap...
    // return transactionList.stream().
    //        collect(Collectors.toMap(Transaction::getCurrency, Function.identity(), BinaryOperator.maxBy(Comparator.comparingInt(Transaction::getSalesValue))));
    static Map<Currency, Transaction> getMapHighestValueByCurrency(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency, collectingAndThen(maxBy(Comparator.comparingInt(Transaction::getSalesValue)), Optional::get)));
    }

    static Map<Currency, List<Transaction>> getTransactionsByCurrencies(final List<Transaction> transactionList) {
        return transactionList
                .stream()
                .collect(groupingBy(Transaction::getCurrency));
    }

}