package org.modernjava.stream.numeric;

import org.modernjava.testdata.transactions.Transaction;

import java.util.List;
import java.util.OptionalInt;

public class NumericStreamMethods {

    // get sum of all sales values
    static int getSumOfAllSalesValues(List<Transaction> transactionList) {
        return transactionList.stream().mapToInt(Transaction::getSalesValue).sum();
    }

    // get max sales value for transaction
    static OptionalInt getMaxSalesValueForTransactions(List<Transaction> transactionList) {
        return transactionList.stream().mapToInt(Transaction::getSalesValue).max();
    }

}
