package com.company;

public class BitCounter {
    public int noOfBits(String number) throws Exception {

        if(number.isEmpty()) {
            return 0;
        }

        int parsedNumber = Integer.parseInt(number);

        if(parsedNumber > 255 || parsedNumber < 0) {
            throw new Exception("Incorrect number");
        }

        String binary = Integer.toBinaryString(parsedNumber);
        int result = 0;
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }
}
