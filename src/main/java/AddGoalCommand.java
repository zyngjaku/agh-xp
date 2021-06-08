import java.math.BigDecimal;


public class AddGoalCommand {

    private final Repository<Goal> goalRepository;

    public AddGoalCommand(Repository<Goal> goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(String input) {
        var cleanInput = input.strip();
        var spacePosition = cleanInput.lastIndexOf(' ');
        if (spacePosition == -1) {
            throw new IllegalArgumentException("Two parameters required");
        }

        var title = cleanInput.substring(0, spacePosition);
        try {
            var total = new BigDecimal(cleanInput.substring(spacePosition + 1));
            goalRepository.add(new Goal(title, total));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input format: " + e.getMessage());
        }
    }
}
