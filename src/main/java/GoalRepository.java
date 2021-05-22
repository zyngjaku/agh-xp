import java.util.ArrayList;


public class GoalRepository {
    private final ArrayList<Goal> goals;

    public GoalRepository() {
        goals = new ArrayList<>();
    }

    public void add(Goal goal) {
        goals.add(goal);
    }

    public ArrayList<Goal> getAll()  {
        return goals;
    }
}