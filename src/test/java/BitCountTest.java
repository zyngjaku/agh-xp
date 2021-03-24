import com.company.BitCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitCountTest {
    BitCounter bitCounter = new BitCounter();

    @Test
    void checkNumberOfBits() throws Exception {
        assertEquals(10, bitCounter.noOfBits("123;89"));
//
//        Exception thrown = assertThrows(
//                Exception.class,
//                () -> bitCounter.noOfBits("256"),
//                "Expected doThing() to throw, but it didn't"
//        );
//
//        Exception thrownMinus = assertThrows(
//                Exception.class,
//                () -> bitCounter.noOfBits("-1"),
//                "Expected doThing() to throw, but it didn't"
//        );
//
//        assertEquals(thrown.getMessage(), "Incorrect number");
//        assertEquals(thrownMinus.getMessage(), "Incorrect number");
    }
}
