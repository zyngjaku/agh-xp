import java.util.Scanner;

public class ExpensesPerCategoryView implements View {

    private final ExpensesPerCategoryCommand expensesPerCategoryCommand;

    public ExpensesPerCategoryView(ExpensesPerCategoryCommand expensesPerCategoryCommand) {
        this.expensesPerCategoryCommand = expensesPerCategoryCommand;
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);

        System.out.print("Expenses summary. Filter category > ");
        var categoryName = scanner.nextLine();

        try {
            var expenseLines = expensesPerCategoryCommand.findByCategory(categoryName);
            if (expenseLines.size() == 0) {
                System.out.println("No expenses found.");
            } else {
                for (var expenseLine : expenseLines) {
                    System.out.println(expenseLine);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
