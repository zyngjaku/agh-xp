import com.company.BitCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitCountTest {
    BitCounter bitCounter = new BitCounter();

    @Test
    void checkNumberOfBits() throws Exception {
        assertEquals(10, bitCounter.noOfBits("123;89"));
        assertEquals(14, bitCounter.noOfBits("123;89 89"));
        assertEquals(5, bitCounter.noOfBits("$E9"));
        assertEquals(15, bitCounter.noOfBits("$E9;123 89"));
        assertEquals(20, bitCounter.noOfBits("$E9;123 89 $E9"));
        assertThrows(BitCounter.NumberException.class, () -> bitCounter.noOfBits("256;34"));
        assertThrows(BitCounter.NumberException.class, () -> bitCounter.noOfBits("-1;34"));
    }
}
