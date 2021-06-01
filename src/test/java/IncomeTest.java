import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class IncomeTest {
    @Test
    public void whenCorrectArgumentsArePassed_thenIncomeIsCreated() {
        var sut = new Income(new BigDecimal("120"), new Date());
        assertEquals(sut.getValue().compareTo(new BigDecimal("120")), 0);
    }

    @Test
    public void whenNegativeValueIsPassed_errorIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> new Income(new BigDecimal("-2"), new Date()));
    }
}
