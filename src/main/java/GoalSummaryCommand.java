import java.util.Date;

public class GoalSummaryCommand {
    private final BalanceCalculator balanceCalculator;
    private final Repository<Goal> goalRepository;

    public GoalSummaryCommand(BalanceCalculator balanceCalculator, Repository<Goal> goalRepository) {
        this.balanceCalculator = balanceCalculator;
        this.goalRepository = goalRepository;
    }

    public String getSummaryText() {
        try {
            var builder = new StringBuilder();
            var todayDate = new Date();
            for (var goal : goalRepository.getAll()) {
                builder.append(new GoalSummary(goal, balanceCalculator.getBalance(todayDate)).getSummary());
                builder.append('\n');
            }
            return builder.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
