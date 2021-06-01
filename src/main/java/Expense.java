import java.math.BigDecimal;
import java.util.Date;

public class Expense {
    private final BigDecimal value;
    private final Date date;

    public BigDecimal getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public Expense(BigDecimal value, Date date) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
        this.value = value;
        this.date = date;
    }
}
