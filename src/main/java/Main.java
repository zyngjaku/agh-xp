import java.security.InvalidParameterException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args){
        var arguments = Arrays.asList(args);
        if (arguments.contains("goalsummary")) {
            var goals = Arrays.asList(
                new Goal("Car", 222),
                new Goal("House", 2222)
            );

            for (var goal : goals) {
                try {
                    System.out.println(new GoalSummary(goal, 2000).getSummary());
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
