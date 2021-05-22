public class GoalSummaryView implements View {
    private final GoalSummaryCommand goalSummaryCommand;

    public GoalSummaryView(GoalSummaryCommand goalSummaryCommand) {
        this.goalSummaryCommand = goalSummaryCommand;
    }

    @Override
    public void execute() {
        System.out.println("Summary:");
        System.out.println(goalSummaryCommand.getSummaryText());
    }
}
