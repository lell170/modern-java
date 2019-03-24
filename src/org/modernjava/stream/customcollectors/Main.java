package org.modernjava.stream.customcollectors;

import org.modernjava.testdata.transactions.TestDataGenerator;
import org.modernjava.testdata.transactions.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        System.out.println(CustomcollectorMethods.getNamesOfAllCitiesCollectorOf(transactionList));
        System.out.println(CustomcollectorMethods.getNamesOfAllCitiesWithCustomCollector(transactionList));
    }

}
