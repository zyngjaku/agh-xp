import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    public int calculate(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        var delimiterRegex = ",|\\n";
        var splitInput = input.split("\n", 2);
        var hasCustomDelimiter = false;

        if (splitInput[0].startsWith("//")) {
            delimiterRegex += "|" + splitInput[0].substring(2);
            hasCustomDelimiter = true;
        }
        return calculateSum(hasCustomDelimiter ? splitInput[1] : input, delimiterRegex);
    }

    private int calculateSum(String numbersInput, String delimiterRegex) {
        var numbers = numbersInput.split(delimiterRegex);
        var sum = 0;
        for (var number : numbers) {
            var value = Integer.parseInt(number);
            if (value < 0) {
                throw new RuntimeException("Value is < 0: " + String.valueOf(value));
            }
            sum += value;
        }
        return sum;
    }

}
