import java.util.Scanner;

public class AddGoalView implements View {
    private final AddGoalCommand addGoalCommand;

    public AddGoalView(AddGoalCommand addGoalCommand) {
        this.addGoalCommand = addGoalCommand;
    }


    @Override
    public void execute() {
        System.out.println("Input format: > title amount");
        System.out.print("Add goal > ");
        var scanner = new Scanner(System.in);
        try {
            addGoalCommand.addGoal(scanner.nextLine());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
