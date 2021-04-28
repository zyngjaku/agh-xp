import java.security.InvalidParameterException;


public class GoalSummary {
    private final double funds;
    private final Goal goal;

    public GoalSummary(Goal goal, double funds) throws InvalidParameterException {
        if (funds < 0) {
            throw new InvalidParameterException("funds has to be positive");
        }
        this.funds = funds;
        this.goal = goal;
    }

    public String getSummary(){
        var remaining = goal.getTotal()  - funds;
        if (remaining > 0) {
            return String.format("%s: %.2f/%.2f PLN remaining", goal.getTitle(), remaining, goal.getTotal());
        }
        return String.format("%s: completed", goal.getTitle());
    }
}
