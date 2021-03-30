import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTests {
    @Test
    public void  Add_AddsUpToTwoNumber_WhenStringIsValid() throws Exception {
        String[] strings = new String[5];
        strings[0] = "";
        strings[1] = "1";
        strings[2] = "1,2";
        strings[3] = "1,2,3";
        strings[4] = "1,2,3,10";
        Calculator cal = new Calculator();
        int result = cal.add(strings[0]);
        Assertions.assertEquals(0,result);
        result = cal.add(strings[1]);
        Assertions.assertEquals(1,result);
        result = cal.add(strings[2]);
        Assertions.assertEquals(3,result);
        result = cal.add(strings[3]);
        Assertions.assertEquals(6,result);
        result = cal.add(strings[4]);
        Assertions.assertEquals(16,result);

    }
    @Test
    public void  Add_AddsUpToAnyNumber_WhenStringIsValid() throws Exception {
        String[] strings = new String[2];
        strings[0] = "1,2,3";
        strings[1] = "1,2,3,10";
        Calculator cal = new Calculator();
        int result = cal.add(strings[0]);
        Assertions.assertEquals(6,result);
        result = cal.add(strings[1]);
        Assertions.assertEquals(16,result);

    }
    @Test
    public void  Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid() throws Exception {
        String[] strings = new String[2];
        strings[0] = "1\n2\n3";
        strings[1] = "1\n2\n3\n10";
        Calculator cal = new Calculator();
        int result = cal.add(strings[0]);
        Assertions.assertEquals(6,result);
        result = cal.add(strings[1]);
        Assertions.assertEquals(16,result);

    }
    @Test
    public void  Add_AddsNumbersUsingAnyDelimiter_WhenStringIsValid() throws Exception {
        String[] strings = new String[2];
        strings[0] = "//#\n1#2#3";
        strings[1] = "//;\n1;2;3;10;100";
        Calculator cal = new Calculator();
        int result = cal.add(strings[0]);
        Assertions.assertEquals(6,result);
        result = cal.add(strings[1]);
        Assertions.assertEquals(116,result);

    }
    @Test
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed(){
        String[] strings = new String[2];
        strings[0] = "//#\n-1#2#3";
        strings[1] = "//;\n1;2;-3;-10;-100";
        Calculator cal = new Calculator();
        String actualMessage = assertThrows(Exception.class, () -> cal.add(strings[0])).getMessage();
        Assertions.assertEquals("negatives not allowed: -1",actualMessage);
        actualMessage = assertThrows(Exception.class, () -> cal.add(strings[1])).getMessage();
        Assertions.assertEquals("negatives not allowed: -3",actualMessage);
    }
}

