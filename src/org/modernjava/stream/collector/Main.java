package org.modernjava.stream.collector;

import org.modernjava.testdata.transactions.TestDataGenerator;
import org.modernjava.testdata.transactions.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        List<Transaction> transactionList = testDataGenerator.generateAndGetTransactionList();

        System.out.println(CollectorMethods.getTransactionsByCurrencies(transactionList));
    }

}
