import com.company.BitCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitCountTest {
    BitCounter bitCounter = new BitCounter();

    @Test
    void checkNumberOfBits() {
        assertEquals(6, bitCounter.noOfBits("123"));
        assertEquals(2, bitCounter.noOfBits("132"));
        assertEquals(4, bitCounter.noOfBits("89"));
    }
}
