import java.math.BigDecimal;

public class GoalFitnesseTest {
    private String title;
    private BigDecimal total;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotal(String total) {
        this.total = new BigDecimal(total);
    }

    public BigDecimal getTotalGoalAmount() {
        Goal goal = new Goal(this.title, this.total);
        return goal.getTotal();
    }
}
