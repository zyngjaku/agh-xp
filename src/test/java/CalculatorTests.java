import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTests {
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "0,1;1",
            "1,2;3",
            "49,48;97",
            "2,2;4",
    }, delimiter = ';')
    public void add_AddsUpToTwoNumbers_WhenStringIsValid(String calculation, int expected) throws Calculator.InvalidCalculation {
        var sut = new Calculator();
        var result = sut.add(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "0,1,2,3,4,5;15",
            "1,2,10,10;23",
            "49,49,2,100,100;300",
    }, delimiter = ';')
    public void add_AddsUpAnyNumbers_WhenStringIsValid(String calculation, int expected) throws Calculator.InvalidCalculation {
        var sut = new Calculator();
        var result = sut.add(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "xD",
            "--",
            "123,49,s",
            "-2,-2,-2,-2,s",
    }, delimiter = ';')
    public void add_ThrowsAnException_WhenStringIsInvalidOrContainsNegativeValues(String calculation) {
        var sut = new Calculator();
        assertThrows(Calculator.InvalidCalculation.class, () -> sut.add(calculation));
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "0%1;1",
            "1%2;3",
            "49%48;97",
            "2%2;4",
    }, delimiter = ';')
    public void add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid(String calculation, int expected) throws Calculator.InvalidCalculation {
        var sut = new Calculator();
        calculation = calculation.replace('%', '\n');
        var result = sut.add(calculation);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "//:0:1;1",
            "//:1:2;3",
            "//:49:48;97",
            "//:2:2;4",
    }, delimiter = ';')
    public void add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid(String calculation, int expected) throws Calculator.InvalidCalculation {
        var sut = new Calculator();
        calculation = calculation.replace('%', '\n');
        var result = sut.add(calculation);

        assertEquals(expected, result);
    }

}
