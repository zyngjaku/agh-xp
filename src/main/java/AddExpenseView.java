import java.util.Scanner;

public class AddExpenseView implements View {
    private final AddExpenseCommand addExpenseCommand;

    public AddExpenseView(AddExpenseCommand addExpenseCommand) {
        this.addExpenseCommand = addExpenseCommand;
    }

    @Override
    public void execute() {
        System.out.println("Input format: value [category] [dd-MM-yyyy date]");
        System.out.print("Add expense > ");
        var scanner = new Scanner(System.in);
        try {
            addExpenseCommand.addExpense(scanner.nextLine());
            System.out.println("Expense added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
