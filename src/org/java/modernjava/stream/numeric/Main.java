package org.java.modernjava.stream.numeric;

import org.java.modernjava.testdata.transactions.TestDataGenerator;
import org.java.modernjava.testdata.transactions.Transaction;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        // get sum of all sales Values for Transaction
        System.out.println(NumericStreamMethods.getSumOfAllSalesValues(transactionList));

        // get max sales value for all Transaction
        System.out.println(NumericStreamMethods.getMaxSalesValueForTransactions(transactionList));

        // get sum of all digits for given n
        System.out.println(NumericStreamMethods.getSum(9999));

        Stream.iterate(1, integer -> integer+2).reduce(Integer::sum);
    }
}
