package org.modernjava.stream.collector;

import org.modernjava.testdata.transactions.Currency;
import org.modernjava.testdata.transactions.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorMethods {

    static Map<Currency, List<Transaction>> getTransactionsByCurrencies(List<Transaction> transactionList) {
        return transactionList.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
    }

}
