import java.util.Scanner;

public class ViewSource {
    private final InputSource inputSource;
    private final RepositoryProvider repositoryProvider;
    private final BalanceProvider balanceProvider;

    public ViewSource(InputSource inputSource, RepositoryProvider repositoryProvider, BalanceProvider balanceProvider) {
        this.inputSource = inputSource;
        this.repositoryProvider = repositoryProvider;
        this.balanceProvider = balanceProvider;
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
        if (command.equals("quit")) {
            return null;
        }

        return new InvalidInputView("Unknown command: " + command);
    }
}
