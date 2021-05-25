public class Month {
    final int year;
    final int month;
    long savings = 0;

    public Month(int month, int year) {
        this.year = year;
        this.month = month;
    }

    public long getSavings() {
        return this.savings;
    }

    public void setSavings(long savings) {
        this.savings = savings;
    }
}