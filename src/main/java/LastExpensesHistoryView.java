import java.util.*;

public class LastExpensesHistoryView implements View {

    private final LastExpensesHistoryCommand command;

    public LastExpensesHistoryView(LastExpensesHistoryCommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        System.out.println("Amount of expenses >");
        var scanner = new Scanner(System.in);

        try {
            for(Expense ex : Objects.requireNonNull(command.getLastExpenses(scanner.nextLine()))) {
                System.out.println("Amount: " + ex.getValue() + " Date:" + ex.getDate());
            }
        } catch (Exception e) {
            System.out.println("No expenses history");
        }
    }
}
