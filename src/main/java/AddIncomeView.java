import java.util.Scanner;

public class AddIncomeView implements View {
    private final AddIncomeCommand addIncomeCommand;

    public AddIncomeView(AddIncomeCommand addIncomeCommand) {
        this.addIncomeCommand = addIncomeCommand;
    }


    @Override
    public void execute() {
        System.out.println("Input format: value [dd-MM-yyyy date]");
        System.out.print("Add income > ");
        var scanner = new Scanner(System.in);
        try {
            addIncomeCommand.addIncome(scanner.nextLine());
            System.out.println("Income added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
