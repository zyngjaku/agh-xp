import java.util.Scanner;


public class ExpensesInMonthView implements View {

    private ExpensesInMonthCommand expensesInMonthCommand;

    public ExpensesInMonthView(ExpensesInMonthCommand expensesInMonthCommand) {
        this.expensesInMonthCommand = expensesInMonthCommand;
    }

    @Override
    public void execute() {
        System.out.println("Input month and year for showing history expenses in format: [MM-yyyy date]");
        var scanner = new Scanner(System.in);
        try {
            var date = scanner.nextLine();
            System.out.println("Expenses in: " + date);
            System.out.println(expensesInMonthCommand.getExpensesHistory(date));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
