
public class Calculator {
    public int add(String numbers) throws InvalidCalculation {
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

        for (var number : numbers.split(delimiters)){
            try{
                var n = Integer.parseInt(number);
                if (n < 0){
                    throw new InvalidCalculation();
                }
                result += n;
            } catch (Exception e){
                throw new InvalidCalculation();
            }

        }

        return result;
    }

    public static class InvalidCalculation extends Exception{

    }
}
