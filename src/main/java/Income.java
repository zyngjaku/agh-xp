import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Income {
    private final BigDecimal value;
    private final Date date;

    public BigDecimal getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public Income(BigDecimal value, Date date) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
        if (value.scale() != 0 && value.scale() != 2) {
            throw new IllegalArgumentException("Invalid number of decimal places. Expected amount format: 123 OR 123.00");
        }
        this.value = value;
        this.date = date;
    }
}
