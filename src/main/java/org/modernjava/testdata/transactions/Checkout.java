package org.modernjava.testdata.transactions;

public class Checkout {

    private int id;

    public Checkout(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                '}';
    }
}
