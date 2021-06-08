import java.math.BigDecimal;
import java.util.Date;

public class Expense {
    private final BigDecimal value;
    private final Date date;
    private final String category;

    public String getCategory() {
        return category;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public Expense(BigDecimal value, Date date, String category) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
        if (value.scale() != 0 && value.scale() != 2) {
            throw new IllegalArgumentException("Invalid number of decimal places. Expected amount format: 123 OR 123.00");
        }
        this.value = value;
        this.date = date;
        if (!category.isBlank()) {
            this.category = category;
        } else {
            this.category = "No category";
        }
    }

    public Expense(BigDecimal value, Date date) {
        this(value, date, "");
    }
}
