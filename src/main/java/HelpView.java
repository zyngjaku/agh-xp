public class HelpView implements View {
    public HelpView() {
    }

    @Override
    public void execute() {
        var text = "Supported commands (case insensitive): AddIncome AddCyclical, AddGoal, GoalSummary, Settings, Help, Quit";
        System.out.println(text);
    }
}
