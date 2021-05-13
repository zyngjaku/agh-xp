import java.util.ArrayList;
import java.util.Arrays;


public class GoalForm {
    public ArrayList<Goal> goals;

    public GoalForm() {
        goals = new ArrayList<>();
    }

    public void addGoal(String name, double amount) {
        var goal = new Goal(name, amount);
        goals.add(goal);
    }
}
