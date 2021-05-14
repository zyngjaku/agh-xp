import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;


public class GoalForm {

    private final GoalRepository goalRepository;

    public GoalForm(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(String name, BigDecimal amount) {
        var goal = new Goal(name, amount);
        goalRepository.add(goal);
    }
}
