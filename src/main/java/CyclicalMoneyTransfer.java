import java.math.BigDecimal;

public class CyclicalMoneyTransfer {
    private BigDecimal amount;
    private int days;

    public CyclicalMoneyTransfer(BigDecimal amount, int days) {
        setAmount(amount);
        setDays(days);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Money amount cannot be zero.");
        }

        this.amount = amount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of days must be greater than zero.");
        }

        this.days = days;
    }

    public String toString() {
        return "[CYCLICAL] Amount " + this.amount + " every " + this.days + " days.";
    }
}
