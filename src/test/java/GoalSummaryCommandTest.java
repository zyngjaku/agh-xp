import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalSummaryCommandTest {
    @Mock
    BalanceCalculator balanceCalculator;

    @Test
    public void whenAddingValidGoalName_isAccepted() {
        var balance = new BigDecimal("500");
        when(balanceCalculator.getBalance(any())).thenReturn(balance);
        var repository = new ListRepository<Goal>();
        repository.add(new Goal("goal1", new BigDecimal(2000)));
        repository.add(new Goal("goal2", new BigDecimal(200)));
        var sut = new GoalSummaryCommand(balanceCalculator, repository);
        var result = sut.getSummaryText();
        var expected = new GoalSummary(repository.getAll().get(0), balance).getSummary()
                + "\n" + new GoalSummary(repository.getAll().get(1), balance).getSummary() + "\n";
        assertEquals(expected, result);
    }
}