public class GoalSummaryCommand {
    private final BalanceProvider balanceProvider;
    private final Repository<Goal> goalRepository;

    public GoalSummaryCommand(BalanceProvider balanceProvider, Repository<Goal> goalRepository) {
        this.balanceProvider = balanceProvider;
        this.goalRepository = goalRepository;
    }

    public String getSummaryText() {
        try {
            var builder = new StringBuilder();
            for (var goal : goalRepository.getAll()) {
                builder.append(new GoalSummary(goal, balanceProvider.getBalance()).getSummary());
                builder.append('\n');
            }
            return builder.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
