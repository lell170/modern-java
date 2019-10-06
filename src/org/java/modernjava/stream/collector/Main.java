package org.java.modernjava.stream.collector;

import org.java.modernjava.testdata.transactions.TestDataGenerator;
import org.java.modernjava.testdata.transactions.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        System.out.println(CollectorMethods.getTransactionsByCurrencies(transactionList));
        System.out.println(CollectorMethods.getCountOfAllTransactions(transactionList));
        System.out.println(CollectorMethods.getSumOfAllTransactionValues(transactionList));
        System.out.println(CollectorMethods.getTransactionWithHighestValue(transactionList));
        System.out.println(CollectorMethods.getTransactionStatistics(transactionList));
        System.out.println(CollectorMethods.getTransactionWithHighestValueByReducing(transactionList));
        System.out.println(CollectorMethods.getMapHighestValueByCurrencyOptional(transactionList));
        System.out.println(CollectorMethods.getMapHighestValueByCurrency(transactionList));
    }

}
