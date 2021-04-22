

public class BitCalculator {
    public int noOfBits1(String numbers) throws InvalidCalculation {
        try {
            if (numbers == null || numbers.equals("")) {
                return 0;
            }
            var result = 0;
            for (var nb : numbers.split("[;\\s]")) {
                int n;
                if (nb.startsWith("$")){
                    n = Integer.parseInt(nb.substring(1), 16);
                } else{
                    n = Integer.parseInt(nb);
                }
                result += getOnes(n);
            }

            return result;
        } catch (Exception e){
            throw new InvalidCalculation();
        }
    }

    private int getOnes(int number) throws InvalidCalculation {
        if (number < 0 || number > 255) {
            throw new InvalidCalculation();
        }

        var result = 0;
        while (number != 0) {
            int d = number % 2;
            if (d == 1)
                result++;
            number = number / 2;
        }
        return result;
    }

    public static class InvalidCalculation extends Exception{

    }
}
