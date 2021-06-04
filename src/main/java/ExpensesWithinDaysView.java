import java.util.Scanner;


public class ExpensesWithinDaysView implements View {

    private ExpensesWithinDaysCommand expensesWithinDaysCommand;

    public ExpensesWithinDaysView(ExpensesWithinDaysCommand expensesWithinDaysCommand) {
        this.expensesWithinDaysCommand = expensesWithinDaysCommand;
    }

    @Override
    public void execute() {
        System.out.println("Input the number of days for which to return the expense history");
        var scanner = new Scanner(System.in);
        try {
            var days = scanner.nextLine();
            System.out.println("Expanses within " + days + " days");
            System.out.println(expensesWithinDaysCommand.getExpansesHistory(days));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
