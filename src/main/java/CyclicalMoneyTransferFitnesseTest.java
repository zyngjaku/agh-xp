import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CyclicalMoneyTransferFitnesseTest {
    BigDecimal amount;
    int days;
    Date dateOfFirstTransfer;

    public void setAmount(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setDateOfFirstTransfer(String date) { this.dateOfFirstTransfer = java.sql.Date.valueOf(LocalDate.parse(date)); }

    public BigDecimal getAmountTest() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days, dateOfFirstTransfer);
        return cyclicalMoneyTransfer.getAmount();
    }

    public int getDaysTest() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days, dateOfFirstTransfer);
        return cyclicalMoneyTransfer.getDays();
    }

    public Date getDateOfFirstTransfer() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days, dateOfFirstTransfer);
        return cyclicalMoneyTransfer.getDateOfFirstTransfer();
    }

    public String toString() {
        CyclicalMoneyTransfer cyclicalMoneyTransfer = new CyclicalMoneyTransfer(this.amount, this.days, dateOfFirstTransfer);
        return cyclicalMoneyTransfer.toString();
    }
}
