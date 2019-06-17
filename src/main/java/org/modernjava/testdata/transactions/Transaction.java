package org.modernjava.testdata.transactions;

public class Transaction {

    private Customer customer;
    private Checkout checkout;
    private int salesValue;
    private int spentTime;
    private int year;
    private Currency currency;

    public Transaction(Customer customer, int salesValue, int spentTime, Checkout checkout, int year, Currency currency) {
        this.customer = customer;
        this.salesValue = salesValue;
        this.spentTime = spentTime;
        this.checkout = checkout;
        this.year = year;
        this.currency = currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(int salesValue) {
        this.salesValue = salesValue;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "\nTransaction{" +
                "\ncustomer=" + customer +
                ",\n checkout=" + checkout +
                ",\n salesValue=" + salesValue +
                ",\n spentTime=" + spentTime +
                ",\n year=" + year +
                "}\n";
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
