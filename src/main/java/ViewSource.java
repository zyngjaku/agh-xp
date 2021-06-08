public class ViewSource {
    private final InputSource inputSource;
    private final RepositoryProvider repositoryProvider;
    private final SettingsProvider settingsProvider;
    private final BalanceCalculator balanceCalculator;

    public ViewSource(InputSource inputSource, RepositoryProvider repositoryProvider, SettingsProvider settingsProvider, BalanceCalculator balanceCalculator) {
        this.inputSource = inputSource;
        this.repositoryProvider = repositoryProvider;
        this.settingsProvider = settingsProvider;
        this.balanceCalculator = balanceCalculator;
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
            return new GoalSummaryView(new GoalSummaryCommand(balanceCalculator, repositoryProvider.getGoalRepository()));
        }
        if (command.equals("settings")) {
            return getSettingsView();
        }
        if (command.equals("addincome")) {
            return new AddIncomeView(new AddIncomeCommand(repositoryProvider.getIncomeRepository()));
        }
        if (command.equals("addexpense")) {
            return new AddExpenseView(new AddExpenseCommand(repositoryProvider.getExpenseRepository()));
        }
        if (command.equals("expensesinmonth")) {
            return new ExpensesInMonthView(new ExpensesInMonthCommand(repositoryProvider.getExpenseRepository()));
        }
        if (command.equals("expenseswithindays")) {
            return new ExpensesWithinDaysView(new ExpensesWithinDaysCommand(repositoryProvider.getExpenseRepository()));
        }
        if (command.equals("lastexpenses")) {
            return new LastExpensesHistoryView(new LastExpensesHistoryCommand(repositoryProvider.getExpenseRepository()));
        }
        if (command.equals("checkbalance")) {
            return new BalanceView(new BalanceCommand(balanceCalculator));
        }
        if (command.equals("expensespercategory")) {
            return new ExpensesPerCategoryView(new ExpensesPerCategoryCommand(repositoryProvider.getExpenseRepository(), settingsProvider));
        }
        if (command.equals("quit")) {
            return null;
        }

        return new InvalidInputView("Unknown command: " + command);
    }
}
