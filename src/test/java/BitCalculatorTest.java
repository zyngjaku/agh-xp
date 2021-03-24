import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitCalculatorTest {
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            ",0",
            "1,1",
            "0,0",
            "31,5",
    })
    public void noOfBits1_CountsOnes_WhenStringIsValid(String calculation, int expected) throws BitCalculator.InvalidCalculation {
        var sut = new BitCalculator();
        var result = sut.noOfBits1(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "-12",
            "256",
            "1000",
            "-20001",
    })
    public void noOfBits1_ShouldThrow_IfNumberIsNotInRange(String calculation) {
        var sut = new BitCalculator();
        assertThrows(BitCalculator.InvalidCalculation.class, () -> sut.noOfBits1(calculation));
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "1;1,2",
            "1;0,1",
            "31;31;31;31;31;1,26",
    })
    public void noOfBits1_CountsOnes_WithMultipleNumbers(String calculation, int expected) throws BitCalculator.InvalidCalculation {
        var sut = new BitCalculator();
        var result = sut.noOfBits1(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "1 1,2",
            "1;0 1,2",
            "31;31 31;31;31 1,26",
    })
    public void noOfBits1_CountsOnes_WithMultipleNumbersDelimitedBySpace(String calculation, int expected) throws BitCalculator.InvalidCalculation {
        var sut = new BitCalculator();
        var result = sut.noOfBits1(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "1 1,2",
            "1;0 1,2",
            "31;31%31\t31;31 1,26",
    })
    public void noOfBits1_CountsOnes_WithMultipleNumbersDelimitedByWhitespace(String calculation, int expected) throws BitCalculator.InvalidCalculation {
        var sut = new BitCalculator();
        calculation = calculation.replace('%','\n');
        var result = sut.noOfBits1(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "12\\,32",
            "asd",
            "77[^&%^",
    })
    public void noOfBits1_ShouldThrow_IfNumberIsInWrongFormat(String calculation) {
        var sut = new BitCalculator();
        assertThrows(BitCalculator.InvalidCalculation.class, () -> sut.noOfBits1(calculation));
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "$ff,8",
            "$ff;255,16",
            "$ff 255;$10,17",
    })
    public void noOfBits1_CountsOnes_WithMultipleNumbersInDecimalAndHexadecimal(String calculation, int expected) throws BitCalculator.InvalidCalculation {
        var sut = new BitCalculator();
        calculation = calculation.replace('%','\n');
        var result = sut.noOfBits1(calculation);

        assertEquals(expected, result);
    }
}
