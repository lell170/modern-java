package org.modernjava.stream.numeric;

import org.modernjava.testdata.transactions.TestDataGenerator;
import org.modernjava.testdata.transactions.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        List<Transaction> transactionList = testDataGenerator.generateAndGetTransactionList();

        // get sum of all sales Values for Transaction
        System.out.println(NumericStreamMethods.getSumOfAllSalesValues(transactionList));

        // get max sales value for all Transaction
        System.out.println(NumericStreamMethods.getMaxSalesValueForTransactions(transactionList));
    }
}
