import java.math.BigDecimal;
import java.util.Date;

public class CyclicalMoneyTransfer {
    private BigDecimal amount;
    private int days;
    private Date dateOfFirstTransfer;

    public CyclicalMoneyTransfer(BigDecimal amount, int days, Date dateOfFirstTransfer) {
        this.dateOfFirstTransfer = dateOfFirstTransfer;
        setAmount(amount);
        setDays(days);
        setDateOfFirstTransfer(dateOfFirstTransfer);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Money amount cannot be zero.");
        }
        if (amount.scale() != 0 && amount.scale() != 2) {
            throw new IllegalArgumentException("Invalid number of decimal places. Expected amount format: 123 OR 123.00");
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

    public Date getDateOfFirstTransfer() {
        return dateOfFirstTransfer;
    }

    public void setDateOfFirstTransfer(Date dateOfFirstTransfer) {
        this.dateOfFirstTransfer = dateOfFirstTransfer;
    }
}
