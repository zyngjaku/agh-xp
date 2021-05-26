import java.math.BigDecimal;
import java.security.InvalidParameterException;


public class GoalSummary {
    private final BigDecimal funds;
    private final Goal goal;

    public GoalSummary(Goal goal, BigDecimal funds) throws InvalidParameterException {
        if (funds.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException("funds has to be non-negative");
        }
        this.funds = funds;
        this.goal = goal;
    }

    public String getSummary() {
        var remaining = goal.getTotal().subtract(funds);
        if (remaining.compareTo(BigDecimal.ZERO) > 0) {
            return String.format("%s: %.2f/%.2f PLN remaining", goal.getTitle(), remaining, goal.getTotal());
        }
        return String.format("%s: completed", goal.getTitle());
    }
}
