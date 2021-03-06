package modernjava.stream.numeric;

import modernjava.testdata.transactions.Transaction;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

class NumericStreamMethods {

    // get sum of all sales values
    static int getSumOfAllSalesValues(final List<Transaction> transactionList) {
        return transactionList.stream().mapToInt(Transaction::getSalesValue).sum();
    }

    // get max sales value for transaction
    static OptionalInt getMaxSalesValueForTransactions(final List<Transaction> transactionList) {
        return transactionList.stream().mapToInt(Transaction::getSalesValue).max();
    }

    // parallel stream
    static long getSum(final long n) {
        return Stream.iterate(1L, i -> i + 1)
                     .limit(n)
                     .parallel()
                     .reduce(0L, Long::sum);
    }


}
