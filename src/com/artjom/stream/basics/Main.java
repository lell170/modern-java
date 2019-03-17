package com.artjom.stream.basics;

import com.artjom.testdata.transactions.Checkout;
import com.artjom.testdata.transactions.Customer;
import com.artjom.testdata.transactions.Transaction;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Test data
        final Checkout checkout_1 = new Checkout(1);
        final Checkout checkout_2 = new Checkout(2);
        final Checkout checkout_3 = new Checkout(3);

        final Customer marc = new Customer("Marc", "Auckland");
        final Customer jennifer = new Customer("Jennifer", "Sidney");
        final Customer daria = new Customer("Daria", "Christchurch");
        final Customer melanie = new Customer("Melanie", "Meran");
        final Customer thomas = new Customer("Thomas", "Sidney");
        final Customer marlen = new Customer("Marlen", "Auckland");

        final List<Transaction> transactionList = Arrays.asList(
                new Transaction(marc, 300, 20, checkout_1, 2018),
                new Transaction(jennifer, 200, 10, checkout_1, 2017),
                new Transaction(daria, 400, 3, checkout_2, 2017),
                new Transaction(marlen, 100, 90, checkout_2, 2019),
                new Transaction(melanie, 1050, 40, checkout_3, 2019),
                new Transaction(thomas, 890, 50, checkout_3, 2019)
        );

        // get all transactionList by year
        System.out.println(BasicTransactionsMethods.findAllByYearAndSort(transactionList, 2019));

        // get all unique supermarkets by name from transaction list
        System.out.println(BasicTransactionsMethods.uniqCityNamesFromCustomers(transactionList));

        // get all customers from given City name
        System.out.println(BasicTransactionsMethods.allCustomersForGivenCity(transactionList, "Auckland"));

        // get all customer names
        System.out.println(BasicTransactionsMethods.allCustomerNames(transactionList));

        // are Customers from given city available?
        System.out.println(BasicTransactionsMethods.areAnyCustomersFromGivenCity(transactionList, "Sidney"));

        // get all sales values for given City
        System.out.println(BasicTransactionsMethods.printAllSalesValuesForGivenCity(transactionList, "Sidney"));

        // get highest sales value of all Transactions
        System.out.println(BasicTransactionsMethods.highestValueOfAllTransactions(transactionList));

        // get Transaction highest sales value from all Transactions
        System.out.println(BasicTransactionsMethods.theSmallestValue(transactionList));

    }

}
