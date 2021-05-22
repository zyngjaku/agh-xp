package Lab1;

import java.util.ArrayList;

public class Calculator {
    public int add(String numbers) throws ArithmeticException {
        int result = 0;

        var delimiters = "[,\\n]";
        if (numbers.startsWith("//")){
            numbers = numbers.replace("//", "");
            delimiters = Character.toString(numbers.charAt(0));
            numbers = numbers.substring(1);
        }


        if (numbers == null){
            return result;
        }
        var negatives = new ArrayList<String>();
        for (var number : numbers.split(delimiters)){
            try{
                var n = Integer.parseInt(number);
                if (n < 0){
                    negatives.add(Integer.toString(n));
                }
                result += n;
            } catch (Exception e){
                throw new ArithmeticException();
            }

        }
        if (!negatives.isEmpty()){
            throw new ArithmeticException("Negatives not allowed - "  + String.join(",", negatives));
        }

        return result;
    }
}
