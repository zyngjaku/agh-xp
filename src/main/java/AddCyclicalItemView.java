import java.util.Scanner;

public class AddCyclicalItemView implements View {
    private final AddCyclicalItemCommand addCyclicalItemCommand;

    public AddCyclicalItemView(AddCyclicalItemCommand addCyclicalItemCommand) {
        this.addCyclicalItemCommand = addCyclicalItemCommand;
    }


    @Override
    public void execute() {
        System.out.println("Input format: > amount days");
        System.out.print("Add cyclical > ");
        var scanner = new Scanner(System.in);
        try {
            addCyclicalItemCommand.addCyclicalItem(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
