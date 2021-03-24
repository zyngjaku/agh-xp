import com.company.BitCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitCountTest {
    BitCounter bitCounter = new BitCounter();

    @Test
    void checkNumberOfBits() throws Exception {
        assertEquals(6, bitCounter.noOfBits("123"));
        assertEquals(2, bitCounter.noOfBits("132"));
        assertEquals(4, bitCounter.noOfBits("89"));
        assertEquals(0, bitCounter.noOfBits(""));

        Exception thrown = assertThrows(
                Exception.class,
                () -> bitCounter.noOfBits("256"),
                "Expected doThing() to throw, but it didn't"
        );

        Exception thrownMinus = assertThrows(
                Exception.class,
                () -> bitCounter.noOfBits("-1"),
                "Expected doThing() to throw, but it didn't"
        );

        assertEquals(thrown.getMessage(), "Incorrect number");
        assertEquals(thrownMinus.getMessage(), "Incorrect number");
    }
}
