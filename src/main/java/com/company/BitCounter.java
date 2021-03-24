package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BitCounter {
    public int noOfBits(String numbers) throws NumberException {

        if(numbers.isEmpty()) {
            return 0;
        }

        int result = 0;

        for(String number : numbers.split("[;\\s]")) {

            if(number.startsWith("$")) {
                result += countOnes(Integer.parseInt(number.substring(1), 16));
            } else {
                result += countOnes(Integer.parseInt(number));
            }
        }
        return result;
    }

    private int countOnes(int number) throws NumberException {
        if (number > 255 || number < 0) {
            throw new NumberException();
        }
        String binary = Integer.toBinaryString(number);
        int result = 0;
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }
    public static class NumberException extends Exception {}
}
