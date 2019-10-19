package modernjava.stream.collector;

import modernjava.testdata.transactions.TestDataGenerator;
import modernjava.testdata.transactions.Transaction;

import java.util.List;

class Main {

    public static void main(final String[] args) {
        final List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

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
