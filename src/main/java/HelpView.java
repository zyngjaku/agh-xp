public class HelpView implements View {
    public HelpView() {
    }

    @Override
    public void execute() {
        var text = "Supported commands (case insensitive): AddIncome AddExpense AddCyclical, AddGoal, GoalSummary, Settings, Help, Quit";
        System.out.println(text);
    }
}
