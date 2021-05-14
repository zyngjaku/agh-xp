import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var arguments = Arrays.asList(args);
        if (arguments.contains("goalsummary")) {
            var goals = Arrays.asList(
                    new Goal("Car", BigDecimal.valueOf(212.2)),
                    new Goal("House", BigDecimal.valueOf(20003.99))
            );

            for (var goal : goals) {
                try {
                    System.out.println(new GoalSummary(goal, BigDecimal.valueOf(290.1)).getSummary());
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                }
            }
        }

        var cyclicalForm = new CyclicalForm();

        var conn = true;
        while (conn) {
            var keyboard = new Scanner(System.in);
            var command = keyboard.nextLine();

            if (command.equals("CYCLICAL")) {
                //CYCLICAL
                cyclicalForm.printCyclicalMoneyTransfer();
            } else if (command.startsWith("CYCLICAL")) {
                //CYCLICAL {VALUE} {DAYS}
                String[] splitted = command.split(" ");
                try {
                    cyclicalForm.addCyclicalMoneyTransfer(new BigDecimal(splitted[1]), Integer.parseInt(splitted[2]));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input parameter: " + e.getMessage());
                }
            }

            if (command.equals("END")) {
                conn = false;
            }
        }
    }
}
