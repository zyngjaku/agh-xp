public class ViewSource {
    private final InputSource inputSource;
    private final RepositoryProvider repositoryProvider;
    private final BalanceProvider balanceProvider;
    private final SettingsProvider settingsProvider;

    public ViewSource(InputSource inputSource, RepositoryProvider repositoryProvider, BalanceProvider balanceProvider, SettingsProvider settingsProvider) {
        this.inputSource = inputSource;
        this.repositoryProvider = repositoryProvider;
        this.balanceProvider = balanceProvider;
        this.settingsProvider = settingsProvider;
    }

    public View getSettingsView() {
        return new SettingsView(new SettingsCommand(settingsProvider));
    }

    public View processNextCommand() {
        var line = inputSource.read();
        if (line == null || line.isEmpty()) {
            return new InvalidInputView("");
        }
        var words = line.split(" ");
        if (words.length == 0) {
            return new InvalidInputView("");
        }
        var command = words[0].toLowerCase();
        if (command.equals("help")) {
            return new HelpView();
        }
        if (command.equals("addcyclical")) {
            return new AddCyclicalItemView(new AddCyclicalItemCommand(repositoryProvider.getCyclicalItemRepository()));
        }
        if (command.equals("addgoal")) {
            return new AddGoalView(new AddGoalCommand(repositoryProvider.getGoalRepository()));
        }
        if (command.equals("goalsummary")) {
            return new GoalSummaryView(new GoalSummaryCommand(balanceProvider, repositoryProvider.getGoalRepository()));
        }
        if (command.equals("settings")) {
            return getSettingsView();
        }
        if (command.equals("addincome")) {
            return new AddIncomeView(new AddIncomeCommand(balanceProvider, repositoryProvider.getIncomeRepository()));
        }
        if (command.equals("quit")) {
            return null;
        }

        return new InvalidInputView("Unknown command: " + command);
    }
}
