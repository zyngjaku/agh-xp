package com.company;

public class BitCounter {
    public int noOfBits(String number) {
        String binary = Integer.toBinaryString(Integer.parseInt(number));

        int result = 0;

        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }
}
