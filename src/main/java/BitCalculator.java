public class BitCalculator {

    public static int noOfBits1(String numbers) throws InvalidCalculation {
        int result = 0;

        if (numbers == null || numbers.equals("")) {
            return result;
        }

        try {
            for (String num : numbers.split("[;, \\t\\n]")) {
                int n;
                if (num.startsWith("$")){
                    n = Integer.parseInt(num.substring(1), 16);
                } else{
                    n = Integer.parseInt(num);
                }

                if (n > 255 || n < 0) {
                    throw new InvalidCalculation(num + " is out of range (0-255)");
                } else {
                    while (n > 0) {
                        result += n & 1;
                        n >>= 1;
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidCalculation("Invalid format of number");
        }

        return result;
    }

    public static class InvalidCalculation extends Exception {
        public InvalidCalculation(String message) {
            System.out.println(message);
        }
    }
}
