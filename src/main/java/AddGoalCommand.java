import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;


public class AddGoalCommand {

    private final Repository<Goal> goalRepository;

    public AddGoalCommand(Repository<Goal> goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(String input) {
        var words = input.split(" ");
        if (words.length < 2) {
            throw new IllegalArgumentException("Two parameters required");
        }
        try {
            var title = words[0];
            var total = new BigDecimal(words[1]);
            goalRepository.add(new Goal(title, total));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Invalid input format: " + e.getMessage());
        }
    }
}
