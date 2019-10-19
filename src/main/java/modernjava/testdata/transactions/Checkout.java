package modernjava.testdata.transactions;

public class Checkout {

    private int id;

    public Checkout(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                '}';
    }
}
