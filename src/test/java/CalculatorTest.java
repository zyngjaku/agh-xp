import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CalculatorTest {
    @ParameterizedTest
    @CsvSource(value = {
            ";0",
            "7;7",
            "0,4;4",
            "1,2;3",
            "22,11;33",
    }, delimiter = ';')
    public void Add_AddsUpToTwoNumbers(String input, int expected) throws Calculator.NegativesNotAllowed {
        assertEquals(expected, Calculator.Add(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2,3,1,7;13",
            "100,102,0,1;203",
    }, delimiter = ';')
    public void Add_AddsUpAnyNumbers(String input, int expected) throws Calculator.NegativesNotAllowed {
        assertEquals(expected, Calculator.Add(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0*1;1",
            "1*2;3",
            "49*48;97",
            "2*2;4",
    }, delimiter = ';')
    public void Add_AddsNumbersSplitByNewLineDelimiter(String input, int expected) throws Calculator.NegativesNotAllowed {
        assertEquals(expected, Calculator.Add(input.replace('*', '\n')));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "//x0x1;1",
            "//@2@2@7;11",
            "//:2:2:7;11",
            "//#1#2#2;5",
    }, delimiter = ';')
    public void add_AddsNumbersUsingCustomDelimiter(String input, int expected) throws Calculator.NegativesNotAllowed {
        assertEquals(expected, Calculator.Add(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,5,-1,12",
            "-2,-3,9,-4",
    }, delimiter = ';')
    public void Add_ThrowsAnExceptionWhenStringContainsNegativeValues(String input) {
        assertThrows(Calculator.NegativesNotAllowed.class, () -> Calculator.Add(input));
    }
}