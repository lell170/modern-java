package org.modernjava.testdata.transactions;

import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {

    public List<Transaction> generateAndGetTransactionList() {
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
        final Customer alex = new Customer("Alex", "Munich");

        return Arrays.asList(
                new Transaction(marc, 300, 20, checkout_1, 2018, Currency.USD),
                new Transaction(jennifer, 200, 10, checkout_1, 2017, Currency.USD),
                new Transaction(daria, 400, 3, checkout_2, 2017, Currency.USD),
                new Transaction(marlen, 100, 90, checkout_2, 2019, Currency.USD),
                new Transaction(melanie, 1050, 40, checkout_3, 2019, Currency.EUR),
                new Transaction(thomas, 890, 50, checkout_3, 2019, Currency.USD),
                new Transaction(alex, 890, 50, checkout_3, 2019, Currency.EUR)
        );
    }

}
