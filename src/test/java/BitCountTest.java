import com.company.BitCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitCountTest {
    BitCounter bitCounter = new BitCounter();

    @Test
    void checkNumberOfBits() {
        assertEquals(6, bitCounter.noOfBits("123"));
    }
}
