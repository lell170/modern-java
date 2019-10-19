package modernjava.stream.basics;

import modernjava.testdata.transactions.TestDataGenerator;
import modernjava.testdata.transactions.Transaction;

import java.util.List;

class Main {

    public static void main(final String[] args) {

        final List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        // get all transactions by year
        System.out.println(BasicStreamMethods.findAllByYearAndSort(transactionList));

        // get all unique supermarkets by name from transaction list
        System.out.println(BasicStreamMethods.getAllForUniqueCityNames(transactionList));

        // get all customers from given City name
        System.out.println(BasicStreamMethods.allCustomersForGivenCity(transactionList, "Auckland"));

        // get all customer names
        System.out.println(BasicStreamMethods.allCustomerNames(transactionList));

        // are Customers from given city available?
        System.out.println(BasicStreamMethods.areAnyCustomersFromGivenCity(transactionList, "Sidney"));

        // get all sales values for given City
        System.out.println(BasicStreamMethods.getAllSalesValuesForGivenCity(transactionList, "Sidney"));

        // get highest sales value of all Transactions
        System.out.println(BasicStreamMethods.getHighestValue(transactionList));

        // get Transaction highest sales value from all Transactions
        System.out.println(BasicStreamMethods.getTransactionWithSmalestValue(transactionList));

    }

}
