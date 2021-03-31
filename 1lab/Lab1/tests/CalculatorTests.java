import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTests {

    @Test
    void givenEmptyInput_returnZero() {
        var sut = new Calculator();
        var actualValue = sut.calculate("");
        assertEquals(0, actualValue);
    }

    @Test
    void givenNullInput_returnZero() {
        var sut = new Calculator();
        var actualValue = sut.calculate(null);
        assertEquals(0, actualValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|0", "5|5", "123|123"}, delimiter = '|')
    void givenOneInteger_returnExpectedValue(String input, int expectedValue) {
        var sut = new Calculator();
        var actualValue = sut.calculate(input);
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2|3", "2,3|5", "9,3|12"}, delimiter = '|')
    void givenTwoIntegers_returnTheirSum(String input, int expectedValue) {
        var sut = new Calculator();
        var actualValue = sut.calculate(input);
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,10,15|30", "30,10,90,1,2,3|136", "1,1,1,0,0|3"}, delimiter = '|')
    void givenMoreThanTwoIntegers_returnTheirSum(String input, int expectedValue) {
        var sut = new Calculator();
        var actualValue = sut.calculate(input);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNewlineBetweenSampleNumbers_returnExpectedSum() {
        var sut = new Calculator();
        var actualValue = sut.calculate("1\n2,5");
        assertEquals(8, actualValue);
    }

    @Test
    void givenCustomDelimiter_returnExpectedSum() {
        var sut = new Calculator();
        var actualValue = sut.calculate("//;\n1;2;3");
        assertEquals(6, actualValue);
    }

    @Test
    void givenInputWithNegativeNumber_throw() {
        var sut = new Calculator();
        assertThrows(RuntimeException.class, () -> {
            sut.calculate("1,5,-7");
        });
    }

    @Test
    void givenInputWithNegativeNumberAndCustomDelimiter_throw(){
        var sut = new Calculator();
        assertThrows(RuntimeException.class, () -> {
            sut.calculate("//;\n9,-5,-6");
        });
    }
}
