import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GoalSummaryTest {
    @Test
    public void getSummary_ReturnsCorrectStringForSummaryWithNotCompletedGoal() throws Exception {
        var goalSummary = new GoalSummary(new Goal("House", BigDecimal.valueOf(6000)), BigDecimal.valueOf(3000));
        var result = goalSummary.getSummary();
        var expected = String.format("%s: %.2f/%.2f PLN remaining", "House", 3000.0, 6000.0);
        assertEquals(expected, result);
    }

    @Test
    public void getSummary_ReturnsCorrectStringForSummaryWithCompletedGoal() throws Exception {
        var goalSummary = new GoalSummary(new Goal("House", BigDecimal.valueOf(6000)), BigDecimal.valueOf(7000));
        var result = goalSummary.getSummary();
        var expected = "House: completed";
        assertEquals(expected, result);
    }

    @Test
    public void getSummary_ThrowsWhenFundsAreNegative() {
        assertThrows(InvalidParameterException.class, () -> new GoalSummary(new Goal("House", BigDecimal.valueOf(6000)), BigDecimal.valueOf(-2)));
    }
}



