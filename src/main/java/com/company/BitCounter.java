package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BitCounter {
    public int noOfBits(String numbers) throws Exception {

        List<String> numbersList = Arrays.asList(numbers.split(";"));

        List<Integer> numbersListParsed = numbersList.stream().map(Integer::parseInt).collect(Collectors.toList());


        if(numbers.isEmpty()) {
            return 0;
        }

//        if(parsedNumber > 255 || parsedNumber < 0) {
//            throw new Exception("Incorrect number");
//        }
        int result = 0;
        for(Integer number : numbersListParsed) {
            String binary = Integer.toBinaryString(number);
            for(int i = 0; i < binary.length(); i++) {
                if(binary.charAt(i) == '1') {
                    result++;
                }
            }
        }
        return result;
    }
}
