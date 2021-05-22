import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalSummaryCommandTest {
    @Mock
    BalanceProvider balanceProvider;
    
    @Test
    public void whenAddingValidGoalName_isAccepted() {
        var balance = new BigDecimal("500");
        when(balanceProvider.getBalance()).thenReturn(balance);
        var repository = new Repository<Goal>();
        repository.add(new Goal("goal1", new BigDecimal(2000)));
        repository.add(new Goal("goal2", new BigDecimal(200)));
        var sut = new GoalSummaryCommand(balanceProvider, repository);
        var result = sut.getSummaryText();
        var expected = new GoalSummary(repository.getAll().get(0), balance).getSummary()
                + "\n" + new GoalSummary(repository.getAll().get(1), balance).getSummary() + "\n";
        assertEquals(expected, result);
    }
}