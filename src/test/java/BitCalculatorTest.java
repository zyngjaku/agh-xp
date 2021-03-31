import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class BitCalculatorTest {
    @ParameterizedTest
    @CsvSource(value = {
            ",0",
            "0,0",
            "11,3",
            "129,2",
    })
    public void noOfBits1_CountsOnesInBinaryRepresentationForSingleNumber(String input, int expected) throws BitCalculator.InvalidCalculation {
        assertEquals(expected, BitCalculator.noOfBits1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-12",
            "330",
            "-275",
    })
    public void noOfBits1_ThrowsAnExceptionIfNumberIsOutOfRange(String input) {
        assertThrows(BitCalculator.InvalidCalculation.class, () -> BitCalculator.noOfBits1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1;2,2",
            "0;8,1",
            "32;32;128;1;2;4,6",
    })
    public void noOfBits1_CountsOnesInBinaryRepresentationForWithMultipleNumbers(String input, int expected) throws BitCalculator.InvalidCalculation {
        assertEquals(expected, BitCalculator.noOfBits1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1;2,2",
            "0 8,1",
            "32;32 128;1 2;4,6",
    })
    public void noOfBits1_CountsOnesInBinaryRepresentationForWithMultipleNumbersDelimitedBySpace(String input, int expected) throws BitCalculator.InvalidCalculation {
        assertEquals(expected, BitCalculator.noOfBits1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1 2,2",
            "0\t8,1",
            "32;32 128*1 2;4,6",
    })
    public void noOfBits1_CountsOnesInBinaryRepresentationForWithMultipleNumbersDelimitedBySpaces(String input, int expected) throws BitCalculator.InvalidCalculation {
        assertEquals(expected, BitCalculator.noOfBits1(input.replace('*','\n')));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "9\\,7",
            "asd",
            "12^155,17&0",
    })
    public void noOfBits1_ThrowsAnExceptionIfNumberIsInWrongFormat(String input) {
        assertThrows(BitCalculator.InvalidCalculation.class, () -> BitCalculator.noOfBits1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "$ff,8",
            "$0b;3,5",
            "$5c 254;$20,12",
    })
    public void noOfBits1_CountsOnesInBinaryRepresentationWithMultipleNumbersInDecimalAndHexadecimal(String input, int expected) throws BitCalculator.InvalidCalculation {
        assertEquals(expected, BitCalculator.noOfBits1(input));
    }
}
