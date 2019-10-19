package modernjava.stream.customcollectors;

import modernjava.testdata.transactions.TestDataGenerator;
import modernjava.testdata.transactions.Transaction;

import java.util.List;

class Main {

    public static void main(final String[] args) {
        final List<Transaction> transactionList = TestDataGenerator.getTestTransactionsData();

        System.out.println(CustomCollectorMethods.getNamesOfAllCitiesCollectorOf(transactionList));
        System.out.println(CustomCollectorMethods.getNamesOfAllCitiesWithCustomCollector(transactionList));
        System.out.println(CustomCollectorMethods.getNamesOfAllCustomers(transactionList));
        System.out.println(CustomCollectorMethods.produceExceptionByWrongUseOfCollect(transactionList));
    }

}
