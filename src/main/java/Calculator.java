import java.util.LinkedList;


public class Calculator {
    public static int Add(String numbers) throws NegativesNotAllowed {
        var sum = 0;
        var delimiters = "[,\\n]";
        var wasNegative = false;
        var negativeValues = new LinkedList<String>();

        if(numbers == null || numbers.equals("")) {
            return sum;
        }

        if (numbers.startsWith("//")) {
            numbers = numbers.replace("//", "");
            delimiters = Character.toString(numbers.charAt(0));
            numbers = numbers.substring(1);
        }

        for(String number : numbers.split(delimiters)) {
            int s = Integer.parseInt(number);
            if (s < 0) {
                wasNegative = true;
                negativeValues.add(number);
            }
            sum += Integer.parseInt(number);
        }

        if(wasNegative) {
            throw new NegativesNotAllowed(negativeValues);
        }

        return sum;
    }

    static class NegativesNotAllowed extends Exception {
        public NegativesNotAllowed(LinkedList<String> negativeValues) {
            System.out.print("Negatives not allowed: ");
            for(String v : negativeValues) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
