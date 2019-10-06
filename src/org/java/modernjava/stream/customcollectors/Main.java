package org.java.modernjava.stream.customcollectors;

import org.java.modernjava.testdata.transactions.TestDataGenerator;
import org.java.modernjava.testdata.transactions.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        System.out.println(CustomCollectorMethods.getNamesOfAllCitiesCollectorOf(transactionList));
        System.out.println(CustomCollectorMethods.getNamesOfAllCitiesWithCustomCollector(transactionList));
        System.out.println(CustomCollectorMethods.getNamesOfAllCustomers(transactionList));
        System.out.println(CustomCollectorMethods.produceExceptionByWrongUseOfCollect(transactionList));
    }

}