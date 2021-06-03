import java.util.*;

public class LastExpensesHistoryView implements View {
    @Override
    public void execute() {
        System.out.println("Amount of expenses >");
        var scanner = new Scanner(System.in);

        try {
            var expensesList = AddExpenseCommand.getExpenseRepository().getAll();
            for(Expense ex : Objects.requireNonNull(LastExpensesHistory.getLastExpenses(scanner.nextLine(), expensesList))) {
                System.out.println("Amount: " + ex.getValue() + " Date:" + ex.getDate());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
