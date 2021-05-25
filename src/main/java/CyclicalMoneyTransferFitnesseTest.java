import java.math.BigDecimal;

public class CyclicalMoneyTransferFitnesseTest {
    BigDecimal amount;
    int days;

    public void setAmount(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BigDecimal getAmountTest() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days);
        return cyclicalMoneyTransfer.getAmount();
    }

    public int getDaysTest() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days);
        return cyclicalMoneyTransfer.getDays();
    }

    public String toString() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days);
        return cyclicalMoneyTransfer.toString();
    }
}
