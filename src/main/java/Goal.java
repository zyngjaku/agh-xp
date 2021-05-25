import java.math.BigDecimal;

public class Goal {
    private final String title;
    private final BigDecimal total;

    public Goal(String title, BigDecimal total) {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("Goal name is null or empty string");
        } else {
            this.title = title;
        }

        if (total.compareTo(BigDecimal.ZERO) > 0) {
            this.total = total;
        } else {
            throw new IllegalArgumentException("Negative or zero goal amount");
        }
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
