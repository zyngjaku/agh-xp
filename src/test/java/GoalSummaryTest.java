import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GoalSummaryTest {
    @Test
    public void getSummary_ReturnsCorrectStringForSummaryWithNotCompletedGoal() throws Exception {
        var goalSummary = new GoalSummary(new Goal("House", 6000), 3000);
        var result = goalSummary.getSummary();
        var expected = "House: 3000.00/6000.00 PLN remaining";
        assertEquals(expected, result);
    }

    @Test
    public void getSummary_ReturnsCorrectStringForSummaryWithCompletedGoal() throws Exception {
        var goalSummary = new GoalSummary(new Goal("House", 6000), 7000);
        var result = goalSummary.getSummary();
        var expected = "House: completed";
        assertEquals(expected, result);
    }

    @Test
    public void getSummary_ThrowsWhenFundsAreNegative() {
        assertThrows(InvalidParameterException.class, () -> new GoalSummary(new Goal("House", 6000), -2));
    }
}



